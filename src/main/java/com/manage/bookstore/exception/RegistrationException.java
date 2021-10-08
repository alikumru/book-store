package com.manage.bookstore.exception;

import lombok.Getter;

@Getter
public class RegistrationException extends Exception{

    private String errorCode;

    public RegistrationException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
