package com.example.bank_api.controller;

import com.example.bank_api.model.Account;
import com.example.bank_api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Test
    public void testCreateAccount() throws Exception {
        String accountId = "1";
        String owner = "CHAYEB";
        double balance = 0;

        Account expectedAccount = new Account(accountId, owner, balance);
        when(accountService.createAccount(anyString())).thenReturn(expectedAccount);

        mockMvc.perform(post("/api/accounts")
                        .param("owner", owner))
                .andExpect(status().isCreated());
    }


}
