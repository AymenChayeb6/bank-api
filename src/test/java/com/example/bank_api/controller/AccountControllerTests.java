package com.example.bank_api.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {

    String accountId = "1";
    String owner = "CHAYEB";
    double balance = 0;
    double amount = 100.0;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testCreateAccount() throws Exception {

        mockMvc.perform(post("/bank_api/accounts")
                        .param("owner", owner))
                .andExpect(status().isCreated());
    }


    @Test
    public void testDepositMoney() throws Exception {

        mockMvc.perform(post("/bank_api/accounts/deposit")
                        .param("accountId", accountId)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
    }


}
