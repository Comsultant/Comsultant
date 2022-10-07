package com.comsultant.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DtoResponse<T> extends MessageResponse {
    T responseDto;

    public DtoResponse(HttpStatus status, String message, T dto) {
        super(status, message);
        this.responseDto = dto;
    }

    public static <T> DtoResponse<T> of(HttpStatus status, String message, T dto) {
        return new DtoResponse<>(status, message, dto);
    }
}
