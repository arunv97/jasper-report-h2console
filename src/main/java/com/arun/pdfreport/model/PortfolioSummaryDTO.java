package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class PortfolioSummaryDTO {
    private String portfolioId;
    private String serviceModel;
    private String mandateType;
    private String bookingCenter;
    private String rmName;
    private String nextReviewDate;
    private String efaAgentCode;
}
