package com.example.restfularticlemanagement.controller;

import com.example.restfularticlemanagement.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.function.EntityResponse;

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
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleArticleException (ArticleNotFoundException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleTagException (TagNotFoundException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleRoleException (RoleNotFoundException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleCategoryException (CategoryNotFoundException exception){
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
    }
}
