package com.springjwtsecurityexample.token.api.exception;
//todo добавить
public class AuthException extends RuntimeException{
    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }
}
