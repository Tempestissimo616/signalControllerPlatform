package com.phoenix.signal.controller.platform.exception;

import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
    }
}
