package com.example.bank_api.service;

import com.example.bank_api.dto.AccountDto;
import com.example.bank_api.mapper.AccountMapper;
import com.example.bank_api.model.Account;
import com.example.bank_api.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTests {
    String accountId = "1";
    String owner = "CHAYEB";
    double balance = 0;


    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    AccountMapper accountMapper;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testCreateAccount() {
        Account expectedAccount = new Account(accountId, owner, balance);
        when(accountRepository.save(any(Account.class))).thenReturn(expectedAccount);

        Account createdAccount = accountService.createAccount(new AccountDto(accountId, owner, balance));
        assertNotNull(createdAccount);
        assertEquals(accountId, createdAccount.getAccountId());
        assertEquals(owner, createdAccount.getOwner());
        assertEquals(balance, createdAccount.getBalance());
    }

    @Test
    void testDepositMoney() {
        Account expectedAccount = new Account(accountId, owner, balance);
        when(accountRepository.save(any(Account.class))).thenReturn(expectedAccount);
        when(accountRepository.findByAccountId(anyString())).thenReturn(Optional.of(expectedAccount));
        when(accountMapper.toEntity(any(AccountDto.class))).thenReturn(expectedAccount);

        accountService.createAccount(new AccountDto(accountId, owner, balance));
        Account updatedAccount = accountService.depositMoney(accountId, 100.0);
        assertEquals(100.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdrawMoney() {
        Account expectedAccount = new Account(accountId, owner, balance);
        when(accountRepository.save(any(Account.class))).thenReturn(expectedAccount);
        when(accountRepository.findByAccountId(anyString())).thenReturn(Optional.of(expectedAccount));

        accountService.createAccount(new AccountDto(accountId, owner, balance));
        accountService.depositMoney(accountId, 100.0);
        Account updatedAccount = accountService.withdrawMoney(accountId, 20.0);
        assertEquals(80.0, updatedAccount.getBalance());
    }


}
