package com.dev.projects.pruebakosmos.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        log.error("Errors: {}", errors);
        log.error("URI: {}", request.getRequestURI());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        log.error("BadRequestException: {}", ex.getMessage());

        Map<String, String> response = new HashMap<>();

        response.put("message", ex.getMessage());

        log.error("Response: {}", response);
        log.error("URI: {}", request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException ex, HttpServletRequest request) {
        log.error("InternalServerErrorException: {}", ex.getMessage());

        Map<String, String> response = new HashMap<>();

        response.put("message", ex.getMessage());

        log.error("Response: {}", response);
        log.error("URI: {}", request.getRequestURI());

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFountException(NotFoundException ex, HttpServletRequest request) {
        log.error("NotFountException: {}", ex.getMessage());

        Map<String, String> response = new HashMap<>();

        response.put("message", ex.getMessage());

        log.error("Response: {}", response);
        log.error("URI: {}", request.getRequestURI());

        return ResponseEntity.internalServerError().body(response);
    }

}
