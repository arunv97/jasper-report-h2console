package com.arun.pdfreport.service;

import com.arun.pdfreport.model.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public JRBeanCollectionDataSource getPortfolioData() {
        List<AccountDTO> accountDtoList = new ArrayList<>();

    
        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNumber("SG011212615");

        List<PortfolioDTO> portfolios = new ArrayList<>();

        
        PortfolioDTO portfolioDto = new PortfolioDTO();
        portfolioDto.setBookingCenter("Singapore");
        portfolioDto.setPortfolioNumber("SG011212615-01");

        
        Owner owner = new Owner();
        owner.setRmFullName("Arun Lee");
        portfolioDto.setOwner(owner);

       
        Summary summary = new Summary();
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setDesc("Discretionary Mandate");
        summary.setServiceModel(serviceModel);
        summary.setMandateType("Focus Equity Asia");
        summary.setPeriodicReviewDate("2025-03-18");
        portfolioDto.setSummary(summary);

       
        // Map<String, Map<String, Fee>> bankingFees = new HashMap<>();
        // Map<String, Fee> newConditionCodeFees = new HashMap<>();
        // Fee bankingServiceFee = new Fee();
        // bankingServiceFee.setEffectivePrice("3000");
        // bankingServiceFee.setFeeType("Banking Service Package");
        // bankingServiceFee.setListPrice("3000");
        // newConditionCodeFees.put("BANKING_SERVICE_FEE", bankingServiceFee);
        // bankingFees.put("newConditionCodeFees", newConditionCodeFees);
        // portfolioDto.setBankingFees(bankingFees);

        portfolios.add(portfolioDto);
        accountDto.setPortfolioDTOList(portfolios);
        accountDtoList.add(accountDto);

        return new JRBeanCollectionDataSource(accountDtoList);
    }
}