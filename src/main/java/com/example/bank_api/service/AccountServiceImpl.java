package com.example.bank_api.service;

import com.example.bank_api.dto.AccountDto;
import com.example.bank_api.exception.AccountNotFoundException;
import com.example.bank_api.exception.InsufficientBalanceException;
import com.example.bank_api.exception.InvalidAmountException;
import com.example.bank_api.mapper.AccountMapper;
import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(new AccountDto(UUID.randomUUID().toString(), accountDto.getOwner(), 0));
        return accountRepository.save(account);
    }

    @Override
    public Account depositMoney(String accountId, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
        Account existingAccount = getExistingAccountByAccountId(accountId);
        existingAccount.setBalance(existingAccount.getBalance() + amount);
        return accountRepository.save(existingAccount);
    }

    @Override
    public Account withdrawMoney(String accountId, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
        Account existingAccount = getExistingAccountByAccountId(accountId);
        if (existingAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account id = " + accountId);
        }
        existingAccount.setBalance(existingAccount.getBalance() - amount);
        return accountRepository.save(existingAccount);
    }

    private Account getExistingAccountByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with account id = " + accountId + " not found"));
    }
}
