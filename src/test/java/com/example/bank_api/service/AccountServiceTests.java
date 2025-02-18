package com.example.bank_api.service;

import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTests {


    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testCreateAccount() {
        String accountId = "1";
        String owner = "CHAYEB";
        double balance = 100.0;

        Account expectedAccount = new Account(accountId, owner, balance);
        when(accountRepository.save(any(Account.class))).thenReturn(expectedAccount);

        Account createdAccount = accountService.createAccount(accountId, owner, balance);
        assertNotNull(createdAccount);
        assertEquals(accountId, createdAccount.getAccountId());
        assertEquals(owner, createdAccount.getOwner());
        assertEquals(balance, createdAccount.getBalance());
    }

}
