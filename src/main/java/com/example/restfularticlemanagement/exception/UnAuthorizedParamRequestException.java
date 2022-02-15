package com.example.restfularticlemanagement.exception;

public class UnAuthorizedParamRequestException extends RuntimeException{
    public UnAuthorizedParamRequestException() {
        super();
    }

    public UnAuthorizedParamRequestException(String message) {
        super(message);
    }
}
