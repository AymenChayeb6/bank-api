package com.example.bank_api.validator;

import com.example.bank_api.exception.InsufficientBalanceException;
import com.example.bank_api.exception.InvalidAmountException;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {
    public void validateAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
    }

    public void validateBalance(double amount, double balance, String accountId) {
        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account id = " + accountId);
        }
    }
}
