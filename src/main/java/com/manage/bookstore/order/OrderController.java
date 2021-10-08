package com.manage.bookstore.order;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.book.BookService;
import com.manage.bookstore.exception.OrderException;
import com.manage.bookstore.statistic.StatisticController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;

@RestController
@RequestMapping(OrderController.ORDER_PATH)
public class OrderController {

    public static final String ORDER_PATH = "/api/order";
    private OrderService orderService;
    private BookService bookService;

    @Autowired
    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Order> getAllOrders() {
        System.out.println("Get Request");
        return null;
    }

    @GetMapping(path = "/{user-id}")
    public ResponseEntity<Order> getOrdersByUser(@PathVariable(name = "user-id") String userId) {
        System.out.println("Get Request With Path Variable");
        return null;
    }

    @PostMapping(path = "")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws OrderException {
        if(order == null)
            return null;

        order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
        orderService.createOrder(order);
        return null;
    }
}
