package com.example.loom.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String text)
    {
        super(text);
    }
}

