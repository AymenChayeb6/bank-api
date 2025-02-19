package com.example.bank_api.controller;

import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import com.example.bank_api.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {

    String accountId = "1";
    String owner = "CHAYEB";
    double amount = 100.0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void testCreateAccount() throws Exception {

        mockMvc.perform(post("/bank_api/accounts")
                        .param("owner", owner))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDepositMoney_shouldReturnNotFound_WhenAccountDoesNotExist() throws Exception {

        mockMvc.perform(post("/bank_api/accounts/deposit")
                        .param("accountId", "123")
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account with account id = 123 not found"));
    }

    @Test
    public void testDepositMoney_shouldReturnOk_WhenAccountExist() throws Exception {
        accountRepository.save(new Account(accountId, owner, 50.0));

        mockMvc.perform(post("/bank_api/accounts/deposit")
                        .param("accountId", accountId)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.balance").value(150.0));
    }

    @Test
    public void testWithdrawMoney_shouldReturnNotFound_WhenAccountDoesNotExist() throws Exception {

        mockMvc.perform(post("/bank_api/accounts/withdraw")
                        .param("accountId", "123")
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account with account id = 123 not found"));
        ;
    }

    @Test
    public void testWithdrawMoney_shouldReturnBadRequest_WhenInsufficientBalance() throws Exception {
        accountRepository.save(new Account(accountId, owner, 50.0));

        mockMvc.perform(post("/bank_api/accounts/withdraw")
                        .param("accountId", accountId)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Insufficient balance in account id = 1"));
    }

    @Test
    public void testWithdrawMoney_shouldReturnOk_WhenAccountExist() throws Exception {
        accountRepository.save(new Account(accountId, owner, 120.0));

        mockMvc.perform(post("/bank_api/accounts/withdraw")
                        .param("accountId", accountId)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.balance").value(20.0));;
    }


}
