package com.manage.bookstore.order;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.book.BookService;
import com.manage.bookstore.exception.OrderException;
import com.manage.bookstore.response.ErrorResponse;
import com.manage.bookstore.response.Response;
import com.manage.bookstore.response.SuccessResponse;
import com.manage.bookstore.statistic.StatisticController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(OrderController.ORDER_PATH)
public class OrderController {

    public static final String ORDER_PATH = "/api/order";
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{user-id}")
    public ResponseEntity<Response> OrdersByUser(@PathVariable(name = "user-id") long userId) throws OrderException {
        List<Order> order = orderService.getOrdersByUser(userId);
        if (order == null) {
            ErrorResponse errorResponse = new ErrorResponse("1008", null, "Order could not found!");
            new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Response successResponse = new SuccessResponse("Orders retrieved successully!", new Date(Calendar.getInstance().getTimeInMillis()), order);
        return new ResponseEntity<Response>(successResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{user-id}/{order-id}")
    public ResponseEntity<Response> OrdersByUserWithDetails(@PathVariable(name = "user-id") long userId,@PathVariable(name = "order-id") long orderId) throws OrderException {
        List<Order> orderDetails = orderService.findOrdersByUserWithDetails(userId,orderId);
        if (orderDetails == null) {
            ErrorResponse errorResponse = new ErrorResponse("1008", null, "Order could not found!");
            new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Response successResponse = new SuccessResponse("Orders retrieved successully!", new Date(Calendar.getInstance().getTimeInMillis()), orderDetails);
        return new ResponseEntity<Response>(successResponse, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Response> createOrder(@RequestBody Order order) throws OrderException {
        try {
            if (order == null) {
                ErrorResponse errorResponse = new ErrorResponse("1001", null, "Order object can not be empty!");
                return new ResponseEntity<Response>(errorResponse, HttpStatus.OK);
            }
            order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
            orderService.createOrder(order);
        } catch (OrderException orderException) {
            ErrorResponse errorResponse = new ErrorResponse("1001", null, orderException.getMessage());
            return new ResponseEntity<Response>(errorResponse, HttpStatus.OK);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse("1001", null, exception.getMessage());
            return new ResponseEntity<Response>(errorResponse, HttpStatus.OK);
        }
        Response successResponse = new SuccessResponse("Order created successully!", new Date(Calendar.getInstance().getTimeInMillis()), null);
        return new ResponseEntity<Response>(successResponse, HttpStatus.OK);
    }
}
