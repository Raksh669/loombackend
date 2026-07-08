package com.example.loom.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException e)
    {
       Map<String,String> fault=new HashMap<>();
       e.getBindingResult().getFieldErrors().forEach(error->{fault.put(error.getField(),error.getDefaultMessage());});
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fault);
    }
     @ExceptionHandler(BusinessValidationException.class)
     public ResponseEntity<?> handleBusinessValidation(BusinessValidationException e)
     {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
     }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    
}
