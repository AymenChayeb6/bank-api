package com.example.bank_api.service;

import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(String accountId, String owner, double balance) {
        Account account = new Account(accountId, owner, balance);
        return accountRepository.save(account);
    }
}
