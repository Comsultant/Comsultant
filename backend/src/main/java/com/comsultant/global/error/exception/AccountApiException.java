package com.comsultant.global.error.exception;

import com.comsultant.global.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class AccountApiException extends RuntimeException{
    private final ErrorCode errorCode;
    public AccountApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}

