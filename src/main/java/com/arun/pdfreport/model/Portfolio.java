package com.arun.pdfreport.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @Column(name = "portfolio_number")
    private String portfolioNumber;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankingFee> bankingFees;

    // Other fields and methods...
}