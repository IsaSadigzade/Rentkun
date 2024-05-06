package com.coders.rentkun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogoNotFoundException extends RuntimeException {
    public LogoNotFoundException(String message) {
        super(message);
    }
}
