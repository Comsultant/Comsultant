package com.comsultant.global.config.security;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.model.AccountErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    public AccountDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Account account = accountRepository.findByEmail(email).orElseThrow(
                () -> new AccountApiException(AccountErrorCode.ACCOUNT_NOT_FOUND)
        );
        return new AccountDetails(account);
    }
}
