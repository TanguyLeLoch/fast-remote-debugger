package com.natu.remotedebugger.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ExceptionResponse> handleFunctionalException(
            TechnicalException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ExceptionResponse> handleFunctionalException(
            NoContentException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


}
