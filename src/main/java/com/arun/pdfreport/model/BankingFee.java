package com.arun.pdfreport.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "banking_fee")
public class BankingFee {

    @Id
    @Column(name = "fee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;

    @Column(name = "list_price")
    private BigDecimal listPrice;

    @Column(name = "effective_price")
    private BigDecimal effectivePrice;

    @Column(name = "fee_type_code")
    private String feeTypeCode;

    @Column(name = "fee_type_desc")
    private String feeTypeDesc;

    @ManyToOne
    @JoinColumn(name = "portfolio_number")
    private Portfolio portfolio;

    // Other fields and methods...

}