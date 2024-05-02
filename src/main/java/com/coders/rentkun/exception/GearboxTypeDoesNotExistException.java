package com.coders.rentkun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GearboxTypeDoesNotExistException extends RuntimeException {
    public GearboxTypeDoesNotExistException(String message) {
        super(message);
    }
}
