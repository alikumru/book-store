package com.manage.bookstore.book;

import com.manage.bookstore.customer.CustomerController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookController.BOOK_PATH)
public class BookController {

    public static final String BOOK_PATH = "/api/book";

    @GetMapping(path = "/books")
    public String books() {
        return "My first Book";
    }


}
