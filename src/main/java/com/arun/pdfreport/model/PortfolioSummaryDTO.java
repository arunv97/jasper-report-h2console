package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class PortfolioSummaryDTO {
    private ServiceModel serviceModel;
    private String mandateType;
    private String periodicReviewDate;
    private String portfolioId;
    private String bookingCenter;
    private String nextReviewDate;
    private String efaAgentCode;
}

