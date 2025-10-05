package com.study.userservice.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.study.userservice.exceptions.IdNotFoundException;
import com.study.userservice.exceptions.InvalidUserCredentialsException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleUserNotFoundException(IdNotFoundException ex) {
    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put("timestamp", LocalDateTime.now());
    errorDetails.put("status", HttpStatus.NOT_FOUND.value());
    errorDetails.put("error", "Not Found");
    errorDetails.put("message", ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidUserCredentialsException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidUserCredentialsException(
      InvalidUserCredentialsException ex) {
    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put("timestamp", LocalDateTime.now());
    errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
    errorDetails.put("error", "Bad Request");
    errorDetails.put("message", ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
