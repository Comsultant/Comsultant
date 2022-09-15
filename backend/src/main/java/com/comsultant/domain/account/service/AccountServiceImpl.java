package com.comsultant.domain.account.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.mapper.AccountMapper;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.model.AccountErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * 회원가입 처리
     * @param accountDto {이메일, 비밀번호, 닉네임, 생년}
     * @return
     */
    @Override
    public boolean registerAccount(AccountDto accountDto) {
        // TODO: 1. 인증된 이메일인지 확인 - REDIS에서 이메일 확인

        // 2. 회원가입 처리
        String encryptedPassword = BCrypt.hashpw(accountDto.getPassword(), BCrypt.gensalt());
        accountDto.encryptPassword(encryptedPassword);
        Account account = accountRepository.save(AccountMapper.mapper.toEntity(accountDto));

        return true;
    }

    /**
     * 중복 이메일 체크
     * @param email 입력 이메일
     * @return 중복이면 False
     */
    @Override
    public boolean checkDuplicatedEmail(String email) {
        Account account = accountRepository.findByEmail(email).orElse(null);
        return account == null;
    }

    /**
     * 중복 닉네임 체크
     * @param nickname 입력 닉네임
     * @return 중복이면 False
     */
    @Override
    public boolean checkDuplicatedNickname(String nickname) {
        Account account = accountRepository.findByNickname(nickname).orElse(null);
        return account == null;
    }
}
