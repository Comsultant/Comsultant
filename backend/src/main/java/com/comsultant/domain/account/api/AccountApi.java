package com.comsultant.domain.account.api;

import com.comsultant.domain.account.dto.AccountDto;
import com.comsultant.domain.account.service.AccountService;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.model.AccountErrorCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApi.class);

    private final AccountService accountService;

    @PostMapping("")
    public ResponseEntity<MessageResponse> registerAccount(@RequestBody AccountDto accountDto) {
        boolean result = accountService.registerAccount(accountDto);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "fail"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MessageResponse> checkDuplicatedEmail(@PathVariable("email") String inputEmail) {
        boolean result = accountService.checkDuplicatedEmail(inputEmail);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "fail"));
        }
    }

    @GetMapping("/name/{nickname}")
    public ResponseEntity<MessageResponse> checkDuplicatedNickname(@PathVariable("nickname") String inputNickname) {
        boolean result = accountService.checkDuplicatedNickname(inputNickname);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "fail"));
        }
    }


}
