package com.manage.bookstore.exception;

public class BookStoreException extends Exception {

    private String errorCode;

    public BookStoreException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
