package com.comsultant.global.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AccountErrorCode implements ErrorCode {
    ACCOUNT_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "User is Not Found"),
//    DUPLICATED_KEY(1001, HttpStatus.INTERNAL_SERVER_ERROR, "Email or Nickname is Duplicated"),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
