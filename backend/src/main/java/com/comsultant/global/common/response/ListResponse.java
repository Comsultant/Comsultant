package com.comsultant.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ListResponse<T> extends MessageResponse {
    List<T> responseList;

    public ListResponse(HttpStatus status, String message, List<T> list) {
        super(status, message);
        this.responseList = list;
    }

    public static <T> ListResponse<T> of(HttpStatus status, String message, List<T> list) {
        return new ListResponse<>(status, message, list);
    }
}