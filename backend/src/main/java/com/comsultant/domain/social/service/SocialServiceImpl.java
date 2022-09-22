package com.comsultant.domain.social.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.domain.social.entity.SnsTypeCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialServiceImpl implements SocialService{
    private final AccountRepository accountRepository;

    /**
     * 회원가입 여부 판단해서 Account Entity 반환
     * @param id
     */
    @Override
    public Account checkSignUp(String id, String birthYear, SnsTypeCode code) {
        Account account = accountRepository.findByEmail(id).orElse(null);
        if(account == null) {
            String nickname = this.generateNickname();
            Account newAccount = Account.builder()
                    .email(id)
                    .nickname(nickname)
                    .birthYear(Integer.parseInt(birthYear))
                    .snsType(code.getCode()).build();
            accountRepository.save(newAccount);
            return newAccount;
        } else {
            return account;
        }
    }

    private String generateNickname() {
        Account tmp = null;
        String nickname = "";
        do {
            nickname = RandomStringUtils.randomNumeric(10);
            tmp = accountRepository.findByNickname(nickname).orElse(null);
        } while (tmp != null);
        return nickname;
    }
}
