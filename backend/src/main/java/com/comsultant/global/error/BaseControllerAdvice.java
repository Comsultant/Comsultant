package com.comsultant.global.error;

import com.comsultant.global.common.response.ErrorResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.error.exception.AccountApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class BaseControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerAdvice.class);

    @ExceptionHandler(AccountApiException.class)
    public ResponseEntity<ErrorResponse> accountApiException(AccountApiException e, HttpServletRequest req) {
        LOGGER.error(req.getRequestURI());
        LOGGER.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        LOGGER.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponse> dataIntegrityViolationException(Exception e, HttpServletRequest req) {
        LOGGER.debug("Data Integrity Violation");
        LOGGER.error(req.getRequestURI());
        LOGGER.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Data Integrity Violation"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> unknownException(Exception e, HttpServletRequest req) {
        LOGGER.debug("UNKNOWN ERROR");
        LOGGER.error(req.getRequestURI());
        LOGGER.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Error"));
    }

}
