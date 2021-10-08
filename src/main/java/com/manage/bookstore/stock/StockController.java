package com.manage.bookstore.stock;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StockController.STOCK_PATH)
public class StockController {

    public static final String STOCK_PATH = "/api/stock";

    private StockService stockService;
    private BookService bookService;

    @Autowired
    public StockController(StockService stockService, BookService bookService) {
        this.stockService = stockService;
        this.bookService = bookService;
    }

    @GetMapping(path = "/{book-id}")
    public ResponseEntity<Object> getStockOfBook(@PathVariable(name = "book-id") String book_id) {
        Stock stock = stockService.findByBookId(Long.valueOf(book_id));
        return new ResponseEntity<Object>(stock, HttpStatus.OK);
    }

    @PostMapping(path = "/{book_id}", params = {"quantity"})
    public ResponseEntity<Object> addStockToBook(@PathVariable(name = "book_id") String book_id, @RequestParam(name = "quantity") int quantity) {
        Book book = bookService.getBook(Long.valueOf(book_id));
        stockService.addStock(book, quantity);
        return new ResponseEntity<Object>(book, HttpStatus.OK);
    }
}
