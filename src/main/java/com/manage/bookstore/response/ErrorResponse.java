package com.manage.bookstore.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends Response {

    private String errorCode;

    public ErrorResponse(String errorCode, String errorMessage, Object body) {
        super(errorMessage, body);
        this.errorCode = errorCode;
    }
}
