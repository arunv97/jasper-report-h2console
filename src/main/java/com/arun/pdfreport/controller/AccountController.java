package com.arun.pdfreport.controller;

import com.arun.pdfreport.model.Account;
import com.arun.pdfreport.model.Portfolio;
import com.arun.pdfreport.service.AccountService;
import java.io.InputStream;
import java.util.HashMap;
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

  private static final Logger logger = LoggerFactory.getLogger(
    AccountController.class
  );

  @Autowired
  private AccountService accountService;

  @GetMapping(
    value = "/{accountNumber}",
    produces = MediaType.APPLICATION_PDF_VALUE
  )
  public ResponseEntity<byte[]> generateReport(
    @PathVariable String accountNumber
  ) {
    try {
      Account account = accountService.getAccountByNumber(accountNumber);
      logger.debug("Account retrieved: {}", account);

      if (account == null) {
        logger.info("Account not found for account number: {}", accountNumber);
        return ResponseEntity.notFound().build();
      }
 // Log portfolios in a readable format
    if (account.getPortfolios() != null) {
        for (Portfolio portfolio : account.getPortfolios()) {
            logger.debug("Portfolio: {}", portfolio);
        }
    } else {
        logger.debug("No portfolios found for account number: {}", accountNumber);
    }
      // Load the main report template
      InputStream mainTemplate = new ClassPathResource(
        "jasper/main_pricing_template.jrxml"
      )
        .getInputStream();
      JasperReport jasperReport = JasperCompileManager.compileReport(
        mainTemplate
      );

      // Prepare the data for the report
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("AccountNumber", account.getAccountNumber());
      // Log parameters
      logger.debug(
        "Parameters passed to Jasper report before sub: {}",
        parameters
      );

      // Add the SUBREPORT_DIR parameter
      parameters.put("SUBREPORT_DIR", "jasper/");

      // Log parameters
      logger.debug("Parameters passed to Jasper report: {}", parameters);

      // Use JRBeanCollectionDataSource for the list of Portfolios
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
        account.getPortfolios()
      );

      // Log portfolios
      logger.debug(
        "Portfolios passed to Jasper report: {}",
        account.getPortfolios()
      );
      logger.debug("Data source for report: {}", dataSource);
      // Fill the report with data
      JasperPrint jasperPrint = JasperFillManager.fillReport(
        jasperReport,
        parameters,
        dataSource
      );

      // Export the report to a PDF format
      byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

      // Log the size of the generated PDF
      logger.debug("Generated PDF size: {} bytes", pdfBytes.length);

      return ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_PDF)
        .body(pdfBytes);
    } catch (Exception e) {
      logger.error(
        "Error generating report for account number: {}",
        accountNumber,
        e
      );
      return ResponseEntity.status(500).body(null);
    }
  }
}
