package com.arun.pdfreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

  @Id
  @Column(name = "account_number")
  private String accountNumber;

  @OneToMany(mappedBy = "account")
  private List<Portfolio> portfolios;
}
