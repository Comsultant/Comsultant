package com.comsultant.domain.account.service;

import com.comsultant.domain.account.dto.AccountDto;

public interface AccountService {
    boolean registerAccount(AccountDto accountDto);
}
