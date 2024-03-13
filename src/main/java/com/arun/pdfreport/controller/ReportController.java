package com.arun.pdfreport.controller;

import com.arun.pdfreport.service.ReportService;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/generatePdf")
    public ResponseEntity generatePdfReport() throws JRException, IOException {
        ClassPathResource reportResource = new ClassPathResource("jasper/Pricing_summary_report.jrxml");
        InputStream reportStream = reportResource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("SUBREPORT_DIR", "jasper/");

        JRBeanCollectionDataSource dataSource = reportService.getPortfolioData();

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

}
