package com.manage.bookstore.exception;

public class OrderException extends Exception{

    private String errorCode;

    public OrderException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
