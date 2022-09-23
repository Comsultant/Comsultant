package com.comsultant.global.error;

import com.comsultant.global.common.response.ErrorResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.error.exception.AccountApiException;
import com.comsultant.global.error.exception.ProductApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice {

    @ExceptionHandler(ProductApiException.class)
    public ResponseEntity<ErrorResponse> productApiException(ProductApiException e, HttpServletRequest req) {
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(AccountApiException.class)
    public ResponseEntity<ErrorResponse> accountApiException(AccountApiException e, HttpServletRequest req) {
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponse> dataIntegrityViolationException(Exception e, HttpServletRequest req) {
        log.debug("Data Integrity Violation");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Data Integrity Violation"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MessageResponse> missingServletRequestParameterException(Exception e, HttpServletRequest req) {
        log.debug("Missing Parameter");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageResponse.of(HttpStatus.BAD_REQUEST, "Missing Parameter"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageResponse> badCredentialsException(Exception e, HttpServletRequest req) {
        log.debug("Unmatched Password");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageResponse.of(HttpStatus.OK, "Wrong Password"));
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<MessageResponse> internalAuthenticationServiceException(Exception e, HttpServletRequest req) {
        log.debug("Auth Failed");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());
        Throwable cause = e.getCause();

        if ( cause instanceof AccountApiException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MessageResponse.of(HttpStatus.OK, "User Not Found"));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MessageResponse.of(HttpStatus.OK, "Auth Failed"));
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResponse> httpMessageNotReadableException (Exception e, HttpServletRequest req) {
        log.debug("HttpMessageNotReadableException");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageResponse.of(HttpStatus.BAD_REQUEST, "Bad Request"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> unknownException(Exception e, HttpServletRequest req) {
        log.debug("UNKNOWN ERROR");
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Error"));
    }

}
