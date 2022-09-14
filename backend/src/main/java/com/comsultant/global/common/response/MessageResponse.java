package com.comsultant.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class MessageResponse {
    HttpStatus status;
    String message;

    public static MessageResponse of(HttpStatus status, String message) {
        return new MessageResponse(status, message);
    }
}
