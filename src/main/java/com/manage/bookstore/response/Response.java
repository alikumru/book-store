package com.manage.bookstore.response;

public class Response {

    public String message;
    public Object body;

    public Response(String message, Object body) {
        this.message = message;
        this.body = body;
    }
}
