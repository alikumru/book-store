package com.manage.bookstore.order;

import com.manage.bookstore.statistic.StatisticController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderController.ORDER_PATH)
public class OrderController {

    public static final String ORDER_PATH = "/api/order";
}
