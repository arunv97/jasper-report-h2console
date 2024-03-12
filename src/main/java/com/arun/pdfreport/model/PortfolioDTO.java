package com.arun.pdfreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDTO {
    private String portfolioNumber;
    private Map<String, Fee> bankingFees;
}
