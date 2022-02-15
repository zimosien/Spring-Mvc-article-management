package com.example.restfularticlemanagement.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
