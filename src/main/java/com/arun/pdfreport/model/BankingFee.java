package com.arun.pdfreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Embeddable
@Table(name = "banking_fees")
public class BankingFee {

  @Column(name = "fee_id")
  private String feeId;

  @Column(name = "list_price")
  private BigDecimal listPrice;

  @Column(name = "effective_price")
  private BigDecimal effectivePrice;

  @Column(name = "fee_type_code")
  private String feeTypeCode;

  @Column(name = "fee_type_desc")
  private String feeTypeDesc;
  // Other fields and methods...
}
