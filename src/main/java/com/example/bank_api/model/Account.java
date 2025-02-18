package com.example.bank_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String accountId;
    private String owner;
    private double balance;
}
