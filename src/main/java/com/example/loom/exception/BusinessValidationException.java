package com.example.loom.exception;

public class BusinessValidationException extends RuntimeException{
    public BusinessValidationException(String text)
    {
        super(text);
    }
}
