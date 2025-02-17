package com.zhaqsylyq.trips.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TripAlreadyInProgressException extends RuntimeException {
    public TripAlreadyInProgressException(String s) {
        super(s);
    }
}
