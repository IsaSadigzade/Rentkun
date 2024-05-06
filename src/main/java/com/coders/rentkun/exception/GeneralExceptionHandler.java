package com.coders.rentkun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> generalExceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BrandNotFoundException.class})
    public ResponseEntity<?> brandNotFoundExceptionHandler(BrandNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BrandDoesNotExistException.class})
    public ResponseEntity<?> brandDoesNotExistExceptionHandler(BrandDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ModelNotFoundException.class})
    public ResponseEntity<?> modelNotFoundExceptionHandler(ModelNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ModelDoesNotExistException.class})
    public ResponseEntity<?> modelDoesNotExistExceptionHandler(ModelDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FeatureNotFoundException.class})
    public ResponseEntity<?> featureNotFoundException(FeatureNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FeatureDoesNotExistException.class})
    public ResponseEntity<?> featureDoesNotExistException(FeatureDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FuelTypeNotFoundException.class})
    public ResponseEntity<?> fuelTypeNotFoundException(FuelTypeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FuelTypeDoesNotExistException.class})
    public ResponseEntity<?> fuelTypeDoesNotExistException(FuelTypeDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {GearboxTypeNotFoundException.class})
    public ResponseEntity<?> gearboxTypeNotFoundException(GearboxTypeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {GearboxTypeDoesNotExistException.class})
    public ResponseEntity<?> gearboxTypeDoesNotExistException(GearboxTypeDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {VehicleTypeNotFoundException.class})
    public ResponseEntity<?> vehicleTypeNotFoundException(VehicleTypeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {VehicleTypeDoesNotExistException.class})
    public ResponseEntity<?> vehicleTypeDoesNotExistException(VehicleTypeDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {VehicleDetailsNotFoundException.class})
    public ResponseEntity<?> vehicleDetailsNotFoundException(VehicleDetailsNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {VehicleDetailsDoesNotExistException.class})
    public ResponseEntity<?> vehicleDetailsDoesNotExistException(VehicleDetailsDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LogoNotFoundException.class})
    public ResponseEntity<?> logoNotFoundException(LogoNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LogoDoesNotExistException.class})
    public ResponseEntity<?> logoDoesNotExistException(LogoDoesNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
