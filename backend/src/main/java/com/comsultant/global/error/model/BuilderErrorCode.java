package com.comsultant.global.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BuilderErrorCode implements ErrorCode {
    PRODUCT_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "Builder is Not Found");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
