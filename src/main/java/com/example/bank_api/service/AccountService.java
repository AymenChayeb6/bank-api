package com.example.bank_api.service;

import com.example.bank_api.model.Account;

public interface AccountService {
    Account createAccount(String owner);
}
