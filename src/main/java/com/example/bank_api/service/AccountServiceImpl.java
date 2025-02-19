package com.example.bank_api.service;

import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(String owner) {
        Account account = new Account(UUID.randomUUID().toString(), owner, 0);
        return accountRepository.save(account);
    }

    @Override
    public Account depositMoney(String accountId, double amount) {
        Account existingAccount = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        existingAccount.setBalance(existingAccount.getBalance() + amount);
        return accountRepository.save(existingAccount);
    }
}
