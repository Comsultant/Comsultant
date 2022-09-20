package com.comsultant.domain.auth.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.auth.dto.AuthDto;

public interface AuthService {
    AuthDto signIn(AccountDto accountDto);
    AuthDto refresh(String refreshToken);
}
