package com.manage.bookstore.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SuccessResponse extends Response{

    private Date date;

    public SuccessResponse(String message, Date date,Object body) {
        super(message,body);
        this.date = date;
    }
}
