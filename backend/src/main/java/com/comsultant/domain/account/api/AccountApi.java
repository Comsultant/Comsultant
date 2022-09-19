package com.comsultant.domain.account.api;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.service.AccountService;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.model.AccountErrorCode;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {
    private final AccountService accountService;

    private final ResponseProperties responseProperties;

    @PostMapping("")
    public ResponseEntity<MessageResponse> registerAccount(@RequestBody AccountDto accountDto) {
        boolean result = accountService.registerAccount(accountDto);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MessageResponse> checkDuplicatedEmail(@PathVariable("email") String inputEmail) {
        boolean result = accountService.checkDuplicatedEmail(inputEmail);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("/name/{nickname}")
    public ResponseEntity<MessageResponse> checkDuplicatedNickname(@PathVariable("nickname") String inputNickname) {
        boolean result = accountService.checkDuplicatedNickname(inputNickname);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @PostMapping("/email/verify-email")
    public ResponseEntity<MessageResponse> sendVerifyEmail(@RequestBody Map<String, String> inputBody) {
        String mailAddress = inputBody.get("email");
        if(mailAddress != null) {
            accountService.sendVerifyEmail(mailAddress);
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("/email/verify-email/{code}")
    public ResponseEntity<MessageResponse> verifyAuthToken(@PathVariable("code") String authToken, @RequestParam("email") String email) {
        if(email == null) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }

        boolean result = accountService.verifyAuthToken(authToken, email);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }
}
