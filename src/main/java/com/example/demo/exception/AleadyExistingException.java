package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AleadyExistingException extends RuntimeException {
    private String field;
    private String message;
}
