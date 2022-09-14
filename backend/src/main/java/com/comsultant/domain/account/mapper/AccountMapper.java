package com.comsultant.domain.account.mapper;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.entity.Account;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends EntityMapper<AccountDto, Account> {
    AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    @Override
    @Mapping(target = "password", constant = "secret")
    AccountDto toDto(final Account entity);

    @Override
    Account toEntity(final AccountDto dto);
}
