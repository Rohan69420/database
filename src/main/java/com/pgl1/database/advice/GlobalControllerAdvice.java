package com.pgl1.database.advice;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLRelatedExceptions(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        log.error("SQL exception triggered", sqlIntegrityConstraintViolationException);
        return new ResponseEntity<>("There is an issue with the SQL side of things. Double check your primary and foreign key constraints.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleUnsupportedMethodExceptions(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
        log.error("Unsupported HTTP request triggered",  httpRequestMethodNotSupportedException);
        return new ResponseEntity<>("This method is not supported, try making a request with a different method", HttpStatus.BAD_REQUEST);
    }
}
