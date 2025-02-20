package com.example.bank_api.controller;

import com.example.bank_api.dto.AccountDto;
import com.example.bank_api.model.Account;
import com.example.bank_api.service.AccountService;
import com.example.bank_api.service.ValidationGroups;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank_api/accounts")
@Tag(name = "AccountController", description = "API for managing bank accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "create a new account")
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Validated(ValidationGroups.CreateAccount.class) @RequestBody AccountDto accountDto) {
        Account createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @Operation(summary = "deposit money into an account")
    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@Validated(ValidationGroups.DepositAndWithdrawMoney.class) @RequestBody AccountDto accountDto) {
        Account updatedAccount = accountService.depositMoney(accountDto.getAccountId(), accountDto.getAmount());
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);

    }

    @Operation(summary = "withdraw money from an account")
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@Validated(ValidationGroups.DepositAndWithdrawMoney.class) @RequestBody AccountDto accountDto) {
        Account updatedAccount = accountService.withdrawMoney(accountDto.getAccountId(), accountDto.getAmount());
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }
}
