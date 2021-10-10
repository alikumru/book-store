package com.manage.bookstore.book;

import com.manage.bookstore.exception.BookStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.BOOK_PATH)
public class BookController {

    public static final String BOOK_PATH = "/api/book";

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Object> getAllBooks() {
        return new ResponseEntity<Object>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{book_id}")
    public ResponseEntity<Book> getStockOfBook(@PathVariable(name = "book_id") String book_id) throws BookStoreException {
        Book book = bookService.getBook(Long.valueOf(book_id));
        if (book == null) {
            throw new BookStoreException("1010", "Book could not be found!");
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody Book book) throws BookStoreException {
        try {
            bookService.save(book);
        } catch (BookStoreException bookStoreException) {
            throw new BookStoreException("1010", bookStoreException.getMessage());
        } catch (Exception e) {
            throw new BookStoreException("1010", e.getMessage());
        }
        return new ResponseEntity<Object>(book, HttpStatus.OK);
    }

    @PutMapping(path = "/{book_id}")
    public ResponseEntity<Object> updateBook(@PathVariable(name = "book_id") long book_id, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "page-number", required = false) int pageNumber) throws BookStoreException {
        try {
            bookService.updateBook(book_id, name, pageNumber);
        } catch (BookStoreException bookStoreException) {
            throw new BookStoreException("1011", bookStoreException.getMessage());
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
