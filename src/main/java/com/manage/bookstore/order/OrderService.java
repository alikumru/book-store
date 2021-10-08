package com.manage.bookstore.order;

import com.manage.bookstore.stock.Stock;
import com.manage.bookstore.stock.StockService;
import com.manage.bookstore.book.Book;
import com.manage.bookstore.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private StockService stockService;

    @Autowired
    public OrderService(OrderRepository orderRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    public void createOrder(Order order) throws OrderException {

        if (order.getAddress() == null)
            throw new OrderException("1001", "User email can not be empty");
        if (order.getOrdered_books() == null)
            throw new OrderException("1001", "Username can not be empty");
        if (order.getUser() == null)
            throw new OrderException("1001", "Password can not be empty");

        Set<Book> books = order.getOrdered_books();

        for (Book book : books) {
            Stock stock = stockService.findByBookId(book.getId());
            if (stock.getStock() < book.getQuantity()) {
                new OrderException("2001", "Book is not exist");
            }
            stock.setStock(stock.getStock() - book.getQuantity());
            stockService.save(stock);
        }
        orderRepository.save(order);
    }
}
