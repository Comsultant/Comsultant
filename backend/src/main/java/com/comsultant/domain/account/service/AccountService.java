package com.comsultant.domain.account.service;

import com.comsultant.domain.account.dto.AccountDto;

public interface AccountService {
    boolean registerAccount(AccountDto accountDto);
    boolean checkDuplicatedEmail(String email);
    boolean checkDuplicatedNickname(String nickname);
    void sendVerifyEmail(String mailAddress);
    boolean verifyAuthToken(String token, String email);
}
