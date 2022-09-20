package com.comsultant.domain.auth.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.config.security.JwtProvider;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.model.AccountErrorCode;
import com.comsultant.global.util.CookieUtil;
import com.comsultant.infra.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AccountRepository accountRepository;
    private final RedisService redisService;

    @Override
    public AuthDto signIn(AccountDto accountDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountDto.getEmail(), accountDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        String nickname = "";
        if ( authentication.getPrincipal() instanceof AccountDetails) {
            nickname =((AccountDetails) authentication.getPrincipal()).getNickname();
        }

        String accessToken = jwtProvider.generateAccessToken(authentication.getName());
        String refreshToken = jwtProvider.generateRefreshToken(authentication.getName());

        return new AuthDto(accessToken, refreshToken, nickname);
    }

    @Override
    public AuthDto refresh(String refreshToken) {
        boolean validation = jwtProvider.validateRefreshToken(refreshToken);
        String email = redisService.getStringValue(refreshToken);
        if(validation && email != null) {
            accountRepository.findByEmail(email).orElseThrow(
                    () -> new AccountApiException(AccountErrorCode.ACCOUNT_NOT_FOUND)
            );
            return AuthDto.builder().accessToken(jwtProvider.generateAccessToken(email)).build();
        } else {
            return null;
        }
    }

    @Override
    public void signOut(HttpServletRequest req) {
        String refreshToken = CookieUtil.searchCookie(req, "refreshToken");
        String accessToken = jwtProvider.resolveToken(req);

        redisService.deleteKey(refreshToken);
        redisService.setStringValueAndExpire(accessToken, "blacklist", jwtProvider.getAccessTokenExpireTime());
    }
}
