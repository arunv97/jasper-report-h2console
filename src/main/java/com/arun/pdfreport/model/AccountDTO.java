package com.arun.pdfreport.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private String accountNumber;
    private GmisDTO gmisDTO;
    List<PortfolioDTO> portfolioDTOList;
}
