package com.coders.rentkun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleTypeNotFoundException extends RuntimeException {
    public VehicleTypeNotFoundException(String message) {
        super(message);
    }
}
