package com.arun.pdfreport.service;

import com.arun.pdfreport.model.Account;
import com.arun.pdfreport.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account getAccountByNumber(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }
}
