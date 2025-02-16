package com.zhaqsylyq.drivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DriverAlreadyExistsException extends RuntimeException{
    public DriverAlreadyExistsException(String message) {
        super(message);
    }
}
