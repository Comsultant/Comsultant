package com.comsultant.domain.auth.api;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final ResponseProperties responseProperties;

    @PostMapping("")
    public ResponseEntity<MessageResponse> signIn() {
////        @RequestBody AccountDto signUser, HttpServletResponse res
////        boolean signResult = true;
////        AuthDto dto = new AuthDto();
//        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));
        log.info("aaaaaaaaaaaaaaaaaaaaaa");
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }
}
