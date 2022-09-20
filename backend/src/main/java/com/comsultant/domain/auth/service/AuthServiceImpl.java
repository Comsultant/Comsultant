package com.comsultant.domain.auth.service;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.config.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

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
}
