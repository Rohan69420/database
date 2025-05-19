package com.pgl1.database.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLRelatedExceptions(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        return new ResponseEntity<>("There is an issue with the SQL side of things. Double check your primary and foreign key constraints.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleUnsupportedMethodExceptions(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
        return new ResponseEntity<>("This method is not supported, try making a request with a different method", HttpStatus.BAD_REQUEST);
    }
}
