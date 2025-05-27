package com.pgl1.database.advice;

import com.pgl1.database.custom.exception.ResourceNotFoundException;
import com.pgl1.database.custom.exception.ResponseNotFoundException;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    public GenericAPIResponse<Object> handleGeneralException(Exception ex, HttpServletRequest request){
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "An unexpected error occured", 1001, request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public GenericAPIResponse<Object> handleResourceNotFoundException(Exception ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()),"Resource not found", 404, request.getRequestURI());
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    public GenericAPIResponse<Object> handleResponseNotFoundException(Exception ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "Response data not found", 204, request.getRequestURI());
    }
}
