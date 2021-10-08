package com.manage.bookstore.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SuccessResponse {

    private String message;
    private Date date;

    public SuccessResponse(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
