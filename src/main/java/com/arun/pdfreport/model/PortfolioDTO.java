package com.arun.pdfreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDTO {
    private String portfolioNumber;
    private PortfolioSummaryDTO summary;
    private String bookingCenter;
    private Owner owner;
}

