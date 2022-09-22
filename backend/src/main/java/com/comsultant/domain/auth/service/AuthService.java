package com.comsultant.domain.auth.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.auth.dto.AuthDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthDto signIn(AccountDto accountDto);
    AuthDto refresh(String refreshToken);
    void signOut(HttpServletRequest req);
    AuthDto socialSignIn(Account account);
}
