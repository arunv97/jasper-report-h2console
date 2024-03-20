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
        List<AccountDTO> accounts = new ArrayList<>();

        // Create a list of PortfolioDTO objects
        List<PortfolioDTO> portfolios = new ArrayList<>();
        PortfolioDTO portfolio1 = new PortfolioDTO();
        portfolio1.setPortfolioNumber("SG091212-01");
        portfolio1.setBookingCenter("Singapore");

        // Create an Owner object with the rmFullName
        Owner owner1 = new Owner("DummyRmFullName1");
        portfolio1.setOwner(owner1);

        // Create a PortfolioSummaryDTO object
        PortfolioSummaryDTO summary1 = new PortfolioSummaryDTO();
        summary1.setPortfolioId("DummyPortfolioId1");
        summary1.setServiceModel(new ServiceModel("DummyServiceModel1"));
        summary1.setBookingCenter("DummyBookingCenter1");
        summary1.setEfaAgentCode("DummyEfaAgentCode1");
        summary1.setMandateType("DummyMandateType1");
        summary1.setNextReviewDate("DummyPeriodReviewDate1");

        // Set the summary to the portfolio
        portfolio1.setSummary(summary1);

        // Add the portfolio to the list of portfolios
        portfolios.add(portfolio1);

        // Create an AccountDTO object and set the list of portfolios
        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNumber("SG011212615");
        accountDto.setPortfolioDTOList(portfolios);

        // Add the account to the list of accounts
        accounts.add(accountDto);

        // Print the value of the data source before returning it
        System.out.println("Accounts Data: " + accounts);

        // Return the data source for the report
        return new JRBeanCollectionDataSource(accounts);
    }
}
