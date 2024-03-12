package com.arun.pdfreport.controller;

import com.arun.pdfreport.model.Fee;
import com.arun.pdfreport.model.PortfolioDTO;
import com.arun.pdfreport.model.AccountDTO;
import com.arun.pdfreport.model.PortfolioDTO;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReportController {

    @GetMapping("/generatePdf")
    public ResponseEntity generatePdfReport() throws JRException, IOException {
        ClassPathResource reportResource = new ClassPathResource("jasper2/Pricing_summary_report.jrxml");
        InputStream reportStream = reportResource.getInputStream();
        // Compile jrxml to Jasper
        JasperReport report = JasperCompileManager.compileReport(reportStream);

        ClassPathResource subReportResource = new ClassPathResource("jasper2/Sub_report.jrxml");
        InputStream subReportStream = subReportResource.getInputStream();
        // Compile jrxml to Jasper
        JasperReport subReport = JasperCompileManager.compileReport(subReportStream);
        // Prepare the parameters for the main report
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("AccountNumber", "SG091212");
        parameters.put("subReport", subReport);
        
        JRBeanCollectionDataSource dataSource = getPortfolioData();
        // Fill the main report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
        dataSource);

        // Export the filled report to a PDF file
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        // Set the HTTP headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        // Return the PDF as a byte array
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);

    }

    private static JRBeanCollectionDataSource getPortfolioData() {
        List<PortfolioDTO> portfolios = new ArrayList<>();

        PortfolioDTO portfolio1 = new PortfolioDTO();
        portfolio1.setPortfolioNumber("SG091212-01");
        Map<String, Fee> fees1 = new HashMap<>();
        fees1.put("BANKING_SERVICE_FEE", new Fee("Banking Service Package", "1299.00", "1222.00"));
        fees1.put("REPORTING_FEE", new Fee("Reporting Fee", "240.00", "240.00"));
        fees1.put("SPECIAL_MAILING", new Fee("Special Mailing", "700.00", "700.00"));
        portfolio1.setBankingFees(fees1);
        portfolios.add(portfolio1);

        PortfolioDTO portfolio2 = new PortfolioDTO();
        portfolio2.setPortfolioNumber("SG091212-022");
        Map<String, Fee> fees2 = new HashMap<>();
        fees2.put("BANKING_SERVICE_FEE", new Fee("Banking Service Package", "1200.00", "1000.00"));
        fees2.put("REPORTING_FEE", new Fee("Reporting Fee", "240.00", "240.00"));
        fees2.put("SPECIAL_MAILING", new Fee("Special Mailing", "700.00", "700.00"));
        portfolio2.setBankingFees(fees2);
        portfolios.add(portfolio2);

        PortfolioDTO portfolio3 = new PortfolioDTO();
        portfolio3.setPortfolioNumber("SG091212-03");
        Map<String, Fee> fees3 = new HashMap<>();
        fees3.put("BANKING_SERVICE_FEE", new Fee("Banking Service Package", "200.00", "20.00"));
        fees3.put("REPORTING_FEE", new Fee("Reporting Fee", "240.00", "240.00"));
        fees3.put("SPECIAL_MAILING", new Fee("Special Mailing", "700.00", "700.00"));
        portfolio3.setBankingFees(fees3);
        portfolios.add(portfolio3);

        List<PortfolioDTO> portfolioDTOList = new ArrayList<>();
        portfolioDTOList.add(portfolio1);
        portfolioDTOList.add(portfolio2);
        portfolioDTOList.add(portfolio3);

        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNumber("SG0976123");
        accountDto.setPortfolioDTOList(portfolioDTOList);

        List<AccountDTO> accountDtoList = new ArrayList<>();
        accountDtoList.add(accountDto);
        System.out.println("accountDtoList: " + accountDtoList);
        return new JRBeanCollectionDataSource(accountDtoList);
    }
}

// accountDto (List<PortfolioData>)
// |
// |-- PortfolioData
//     |
//     |-- List<Portfolio> portfolios
//         |
//         |-- Portfolio
//             |
//             |-- String portfolioNumber
//             |
//             |-- Map<String, Fee> bankingFees
//                 |
//                 |-- "BANKING_SERVICE_FEE" -> Fee(feeType, listPrice, effectivePrice)
//                 |-- "REPORTING_FEE" -> Fee(feeType, listPrice, effectivePrice)
//                 |-- "SPECIAL_MAILING" -> Fee(feeType, listPrice, effectivePrice)