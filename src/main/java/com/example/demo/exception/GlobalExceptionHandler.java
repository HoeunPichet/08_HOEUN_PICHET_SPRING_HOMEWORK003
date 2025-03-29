package com.example.demo.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppNotFoundException.class)
    public ProblemDetail handleException(AppNotFoundException e) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setDetail(e.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());

        return detail;
    }

    @ExceptionHandler(AleadyExistingException.class)
    public ProblemDetail handleExistingDataException(AleadyExistingException e) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setDetail(e.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());

        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgument(MethodArgumentNotValidException e) {
        Map<String , String > errors = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setDetail("BAD REQUEST");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);

        return detail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handlerMethodValidationException(HandlerMethodValidationException e) {
        List<String> errors = new ArrayList<>();
        for (MessageSourceResolvable pathError : e.getAllErrors()) {
            errors.add(pathError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setDetail("BAD REQUEST");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);

        return detail;
    }
}
