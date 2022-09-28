package com.comsultant.domain.auth.api;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.domain.auth.service.AuthService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final ResponseProperties responseProperties;
    private final AuthService authService;

    @PostMapping("")
    public ResponseEntity<DtoResponse<AuthDto>> signIn(@RequestBody AccountDto signUser, HttpServletResponse res) {
        AuthDto dto = authService.signIn(signUser);
        if(dto != null) {
            CookieUtil.setRefreshTokenCookie(res, dto.getRefreshToken());
            dto.hideRefreshToken();
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<DtoResponse<AuthDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtil.searchCookie(request, "refreshToken");
        if(refreshToken != null && !refreshToken.equals("")) {
            AuthDto dto = authService.refresh(refreshToken);

            if(dto == null) {
                CookieUtil.deleteRefreshTokenCookie(response);
                return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }

    @DeleteMapping("")
    public ResponseEntity<MessageResponse> signOut(HttpServletRequest request, HttpServletResponse response){
        authService.signOut(request);
        CookieUtil.deleteRefreshTokenCookie(response);

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }
}
