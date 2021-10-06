package com.manage.bookstore.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SuccessResponse {

    private String message;
    private Date date;
}
