package com.comsultant.global.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BuilderErrorCode implements ErrorCode {
    PRODUCT_NOT_FOUND(3000, HttpStatus.NOT_FOUND, "Product is Not Found"),
    Builder_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "Builder is Not Found"),
    Kafka_SEND_FAIL(3002, HttpStatus.INTERNAL_SERVER_ERROR, "Kafka send Fail"),
    Category_NOT_ENOUGH(3003, HttpStatus.BAD_REQUEST, "Category is Not Enough");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
