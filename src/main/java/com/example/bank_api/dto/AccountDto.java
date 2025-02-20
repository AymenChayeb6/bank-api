package com.example.bank_api.dto;


import com.example.bank_api.service.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    @NotBlank(message = "Account id is required", groups = ValidationGroups.DepositAndWithdrawMoney.class)
    private String accountId;

    @NotBlank(message = "Owner name is required", groups = ValidationGroups.CreateAccount.class)
    private String owner;

    private double balance;
    private double amount;

    public AccountDto(String accountId, String owner, double balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
    }
}
