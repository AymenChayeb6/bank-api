package com.example.bank_api.mapper;

import com.example.bank_api.dto.AccountDto;
import com.example.bank_api.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    // Convert from Account to AccountDto
    AccountDto toDto(Account account);

    // Convert from AccountDto to Account
    Account toEntity(AccountDto accountDto);
}
