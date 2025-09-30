package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handle(BindException exception) {
        Assert.isTrue(exception.getAllErrors().size() == 1, "Only one error should be present");
        var fieldError = exception.getFieldError();

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(fieldError.getDefaultMessage());
    }
}
