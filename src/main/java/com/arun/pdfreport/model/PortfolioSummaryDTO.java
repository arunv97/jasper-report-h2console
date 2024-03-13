package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class PortfolioSummaryDTO {
    private String portfolioId;
    private String serviceModel;
    private String mandateType;
    private String investmentStrategy;
    private String bookingCenter;
    private String rm;
    private String rmTeamName;
    private String periodReviewDate;
    private String efaAgentCode;
}
