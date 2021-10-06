package com.manage.bookstore.exception;

public class RegistrationException extends Exception{

    private String errorCode;

    public RegistrationException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
