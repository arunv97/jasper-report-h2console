package com.arun.pdfreport.controller;

import com.arun.pdfreport.model.Account;
import com.arun.pdfreport.model.BankingFee;
import com.arun.pdfreport.service.AccountService;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jasper")
public class AccountController {

  private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountService accountService;

  @GetMapping(value = "/{accountNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> generateReport(@PathVariable String accountNumber) {
    try {
      Account account = accountService.getAccountByNumber(accountNumber);
      if (account == null) {
        logger.info("Account not found for account number: {}", accountNumber);
        return ResponseEntity.notFound().build();
      }

      Map<String, Object> parameters = prepareParameters(account);

      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((List) parameters.get("Portfolios"));

      JasperPrint jasperPrint = createJasperPrint(parameters, dataSource);

      byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

      return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
    } catch (Exception e) {
      logger.error("Error generating report for account number: {}", accountNumber, e);
      return ResponseEntity.status(500).body(null);
    }
  }

  private Map<String, Object> prepareParameters(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("AccountNumber", account.getAccountNumber());
    parameters.put("SUBREPORT_DIR", "jasper/");
    parameters.put("Portfolios", mapPortfolios(account));
    return parameters;
}

  private List<Map<String, Object>> mapPortfolios(Account account) {
    List<Map<String, Object>> portfolioDataList = new ArrayList<>();
    account.getPortfolios().forEach(portfolio -> {
      Map<String, Object> portfolioData = new HashMap<>();
      portfolioData.put("portfolioNumber", portfolio.getPortfolioNumber());
      portfolioData.put("bankingFees", portfolio.getBankingFees());

      BigDecimal additionalBankingFees = portfolio.getBankingFees().stream()
          .filter(fee -> "special_mailing".equals(fee.getFeeTypeCode()) || "reporting_fee".equals(fee.getFeeTypeCode()))
          .map(BankingFee::getEffectivePrice)
          .reduce(BigDecimal.ZERO, BigDecimal::add);

      portfolioData.put("additionalBankingFees", List.of(additionalBankingFees));
      portfolioDataList.add(portfolioData);
    });

    return portfolioDataList;
  }

  private JasperPrint createJasperPrint(Map<String, Object> parameters, JRBeanCollectionDataSource dataSource)
      throws JRException, IOException {
    InputStream mainTemplate = new ClassPathResource("jasper/main_pricing_template.jrxml").getInputStream();
    JasperReport jasperReport = JasperCompileManager.compileReport(mainTemplate);
    return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
  }
}