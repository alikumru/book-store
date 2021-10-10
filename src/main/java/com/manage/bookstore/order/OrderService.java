package com.manage.bookstore.order;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.book.BookService;
import com.manage.bookstore.exception.BookStoreException;
import com.manage.bookstore.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private BookService bookService;
    private OrderDetailsService orderDetailsService;

    @Autowired
    public OrderService(OrderRepository orderRepository, BookService bookService, OrderDetailsService orderDetailsService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.orderDetailsService = orderDetailsService;
    }

    public void createOrder(Order order) throws OrderException, BookStoreException {

        if (order == null)
            throw new OrderException("1001", "Order can not be empty");
        if (order.getAddress() == null)
            throw new OrderException("1001", "Address can not be empty");
        if (order.getUser() == null)
            throw new OrderException("1003", "User can not be empty");

        Set<OrderDetails> orderDetails = order.getOrderDetails();


        for (OrderDetails orderDetail : orderDetails) {
            if (orderDetail.getQuantity() <= 0)
                continue;
            Book book = bookService.getBook(orderDetail.getBook().getId());
            if (book == null)
                throw new OrderException("1004", "Book is not exist");

            if (orderDetail.getQuantity() > book.getStock()) {
                throw new OrderException("1005", "There is not enough book to order");
            }
            book.setStock(book.getStock() - orderDetail.getQuantity());
            bookService.save(book);
            orderDetailsService.save(orderDetail);
        }

        orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(long userId) throws OrderException {
        return orderRepository.findOrdersByUser(userId);
    }

    public List<Order> findOrdersByUserWithDetails(long userId, long orderId) throws OrderException {
        return orderRepository.findOrdersByUserWithDetails(userId, orderId);
    }

    public List<Order> getOrdersSince(long userId, Date since) throws OrderException {
        return orderRepository.findUserOrdersSince(userId, since);
    }

    public List<Order> getOrdersSinceTo(long userId, Date since, Date to) throws OrderException {
        return orderRepository.findUserOrdersSinceTo(userId, since, to);
    }
}
