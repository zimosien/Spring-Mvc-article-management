package com.example.restfularticlemanagement.controller;

import com.example.restfularticlemanagement.exception.ExceptionResponse;
import com.example.restfularticlemanagement.exception.UnAuthorizedParamRequestException;
import com.example.restfularticlemanagement.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleUserException (UserNotFoundException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleUnauthorizedParamRequestException (UnAuthorizedParamRequestException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.UNAUTHORIZED);
    }
}
