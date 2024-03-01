package com.arun.pdfreport.model;

import jakarta.persistence.*;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @Column(name = "portfolio_number")
    private String portfolioNumber;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;

    @ElementCollection
    @CollectionTable(name = "banking_fees")
    @MapKeyColumn(name = "fee_key")
    @Column(name = "fee_value")
    private Map<String, BankingFee> bankingFees;
    // Other fields and methods...
}
