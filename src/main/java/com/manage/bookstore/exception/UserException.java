package com.manage.bookstore.exception;

public class UserException extends Exception{

    private String errorCode;

    public UserException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
