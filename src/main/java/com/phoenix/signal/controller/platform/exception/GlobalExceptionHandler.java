package com.phoenix.signal.controller.platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetail> handleConflictException(ConflictException e, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(
                "CONFLICT",
                e.getMessage(),
                webRequest.getDescription(false),
                webRequest.getContextPath(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetail> handleNotFoundException(Exception e, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                "NOT_FOUND",
                e.getMessage(),
                webRequest.getDescription(false),
                webRequest.getContextPath(),
                LocalDateTime.now()
        );
        return  new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDetail> handleIOException(IOException e, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(
                "INTERNAL SERVER ERROR",
                e.getMessage(),
                webRequest.getDescription(false),
                webRequest.getContextPath(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception e, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(
                "INTERNAL SERVER ERROR",
                e.getMessage(),
                webRequest.getDescription(false),
                webRequest.getContextPath(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
