package com.arun.pdfreport.repository;

import com.arun.pdfreport.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// @Repository
// public interface AccountRepository extends JpaRepository<Account, String> {
//   @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
//   Account findByAccountNumber(String accountNumber);
// }

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
  @Query(
    "SELECT a FROM Account a JOIN FETCH a.portfolios WHERE a.accountNumber = :accountNumber"
  )
  Account findByAccountNumber(String accountNumber);
}
