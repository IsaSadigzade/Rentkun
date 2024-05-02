package com.coders.rentkun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FeatureDoesNotExistException extends RuntimeException {

    public FeatureDoesNotExistException(String message) {
        super(message);
    }
}
