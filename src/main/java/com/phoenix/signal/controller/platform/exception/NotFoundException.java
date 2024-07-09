package com.phoenix.signal.controller.platform.exception;

import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
    }
}
