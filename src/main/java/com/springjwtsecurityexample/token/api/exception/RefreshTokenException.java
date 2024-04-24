package com.springjwtsecurityexample.token.api.exception;

public class RefreshTokenException extends RuntimeException{
    public RefreshTokenException() {
    }

    public RefreshTokenException(String message) {
        super(message);
    }
}
