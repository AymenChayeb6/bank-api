package com.example.bank_api.controller;

import com.example.bank_api.exception.AccountNotFoundException;
import com.example.bank_api.exception.InsufficientBalanceException;
import com.example.bank_api.model.Account;
import com.example.bank_api.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank_api/accounts")
@Tag(name = "AccountController", description = "API for managing bank accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "create a new account")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam String owner) {
        Account createdAccount = accountService.createAccount(owner);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @Operation(summary = "deposit money into an account")
    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestParam String accountId, @RequestParam double amount) {
        try {
            Account updatedAccount = accountService.depositMoney(accountId, amount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "withdraw money from an account")
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestParam String accountId, @RequestParam double amount) {
        try {
            Account updatedAccount = accountService.withdrawMoney(accountId, amount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InsufficientBalanceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
