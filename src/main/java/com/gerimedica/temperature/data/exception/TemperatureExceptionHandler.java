package com.gerimedica.temperature.data.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class TemperatureExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(final Exception ex) {
        log.error("Exception occurred", ex);
        final ApiError apiError = new ApiError(ex.getMessage());
        return new ResponseEntity(apiError, INTERNAL_SERVER_ERROR);
    }

}
