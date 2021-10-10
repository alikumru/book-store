package com.manage.bookstore.order;

import com.manage.bookstore.book.BookService;
import com.manage.bookstore.book.Book;
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

    @Autowired
    public OrderService(OrderRepository orderRepository, BookService bookService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public void createOrder(Order order) throws OrderException, BookStoreException {

        if (order.getAddress() == null)
            throw new OrderException("1001", "User email can not be empty");
        if (order.getOrdered_books() == null)
            throw new OrderException("1002", "Username can not be empty");
        if (order.getUser() == null)
            throw new OrderException("1003", "Password can not be empty");

        Set<Book> books = order.getOrdered_books();

        for (Book orderedBook : books) {
            Book book = bookService.getBook(orderedBook.getId());
            if (book == null)
                throw new OrderException("1004", "Book is not exist");

            if (orderedBook.getStock() > book.getStock()) {
                throw new OrderException("1005", "There is not enough book to order");
            }
            book.setStock(book.getStock() - orderedBook.getStock());
            bookService.save(book);
        }
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(long userId) throws OrderException {
        return orderRepository.findOrdersByUser(userId);
    }

    public List<Order> getOrdersSince(long userId, Date since) throws OrderException {
        return orderRepository.findUserOrdersSince(userId, since);
    }

    public List<Order> getOrdersSinceTo(long userId, Date since, Date to) throws OrderException {
        return orderRepository.findUserOrdersSinceTo(userId, since, to);
    }
}
