package com.example.bank_api.service;

import com.example.bank_api.dto.AccountDto;
import com.example.bank_api.model.Account;

public interface AccountService {
    Account createAccount(AccountDto accountDto);
    Account depositMoney(String accountId, double amount);
    Account withdrawMoney(String accountId, double amount);
}
