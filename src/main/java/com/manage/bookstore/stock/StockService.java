package com.manage.bookstore.stock;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StockService {

    private StockRepository stockRepository;
    private BookService bookService;

    @Autowired
    public StockService(StockRepository stockRepository, BookService bookService) {
        this.stockRepository = stockRepository;
        this.bookService = bookService;
    }

    public void addStock(Book book,int stock){
        stockRepository.save(new Stock(book,stock));
    }
    public void findAll() {
        stockRepository.findAll();
    }

    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock findByBookId(long book_id) {
        return stockRepository.findStockByBookId(book_id);
    }

}
