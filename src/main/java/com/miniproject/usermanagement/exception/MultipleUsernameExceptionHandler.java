package com.miniproject.usermanagement.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MultipleUsernameExceptionHandler {

    @ExceptionHandler(value = {MultipleUsernameException.class})
    public ResponseEntity<Object> handleMultipleUsernameException(MultipleUsernameException e) {
        UsernameException usernameException = new UsernameException(
            e.getMessage(), 
            null, 
            HttpStatus.BAD_REQUEST,
            ZonedDateTime.now(ZoneId.of("Z")) 
        );

        return new ResponseEntity<>(usernameException, HttpStatus.BAD_REQUEST);
    } 

}
