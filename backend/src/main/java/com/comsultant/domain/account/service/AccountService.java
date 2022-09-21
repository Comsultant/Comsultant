package com.comsultant.domain.account.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.dto.PasswordDto;
import com.comsultant.global.config.security.AccountDetails;

public interface AccountService {
    boolean registerAccount(AccountDto accountDto);
    boolean checkDuplicatedEmail(String email);
    boolean checkDuplicatedNickname(String nickname);
    void sendVerifyEmail(String mailAddress);
    boolean verifyAuthToken(String token, String email);
    AccountDto getProfile(AccountDetails accountDetails);
    boolean modifyAccount(AccountDetails accountDetails, AccountDto accountDto);
    boolean modifyPassword(AccountDetails accountDetails, PasswordDto passwordDto);
}
