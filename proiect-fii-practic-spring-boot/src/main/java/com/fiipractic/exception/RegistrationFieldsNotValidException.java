package com.fiipractic.exception;

public class RegistrationFieldsNotValidException extends RuntimeException{
    public RegistrationFieldsNotValidException(String message) {
        super(message);
    }
}
