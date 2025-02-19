package com.example.bank_api.service;

import com.example.bank_api.exception.AccountNotFoundException;
import com.example.bank_api.exception.InsufficientBalanceException;
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
                .orElseThrow(() -> new AccountNotFoundException("Account with account id = " + accountId + " not found"));
        existingAccount.setBalance(existingAccount.getBalance() + amount);
        return accountRepository.save(existingAccount);
    }

    @Override
    public Account withdrawMoney(String accountId, double amount) {
        Account existingAccount = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with account id = " + accountId + " not found"));
        if (existingAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account id = " + accountId);
        }
        existingAccount.setBalance(existingAccount.getBalance() - amount);
        return accountRepository.save(existingAccount);
    }
}
