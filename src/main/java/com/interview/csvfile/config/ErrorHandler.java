package com.interview.csvfile.config;

import com.interview.csvfile.exception.ErrorObject;
import com.interview.csvfile.exception.WrongInputFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(WrongInputFileException.class)
    public ResponseEntity<ErrorObject> handleDataNotFoundException(WrongInputFileException exception) {

        ErrorObject errorObject = ErrorObject.builder()
                .title("Unexpected Error occurred")
                .description(exception.getCause().getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
