package com.comsultant.global.error.exception;

import com.comsultant.global.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class ProductApiException extends RuntimeException{
    private final ErrorCode errorCode;
    public ProductApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}

