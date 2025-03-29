package com.example.demo.exception;

public class AleadyExistingException extends RuntimeException {
    public AleadyExistingException(String message) {
        super(message);
    }
}
