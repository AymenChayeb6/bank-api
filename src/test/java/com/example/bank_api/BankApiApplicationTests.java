package com.example.bank_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankApiApplicationTests {


    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService();
    }

    @Test
    void testCreateAccount() throws Exception {
        String accountId = "1";
        double balance = 100.0;

        Account expectedAccount = new Account(accountId, balance);
        when(accountRepository.createAccount(any(Account.class))).thenReturn(expectedAccount);

        Account createdAccount = accountService.createAccount(accountId, balance);
        assertNotNull(createdAccount);
        assertEquals(accountId, createdAccount.getAccountId);
        assertEquals(balance, createdAccount.getBalance);
    }

}
