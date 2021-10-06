package com.manage.bookstore.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerController.CUSTOMER_PATH)
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/customer";
}
