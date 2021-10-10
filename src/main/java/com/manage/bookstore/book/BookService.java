package com.manage.bookstore.book;

import com.manage.bookstore.exception.BookStoreException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    private void createBooks() {
        bookRepository.save(new Book("Suç ve Ceza", 580, 100, 12.35f));
        bookRepository.save(new Book("Aşk-ı Memnu", 380, 40,23.40f));
        bookRepository.save(new Book("Yaprak Dökümü", 200, 60,18.70F));
        bookRepository.save(new Book("Beyaz Diş", 450, 120,7.4F));
        bookRepository.save(new Book("Kuyucaklı Yusuf", 220, 3,20F));
        bookRepository.save(new Book("Olasılıksız", 500, 15,39F));
        bookRepository.save(new Book("Empati", 550, 3,15.2F));
        bookRepository.save(new Book("Savaş Ve Barış", 600, 500,5F));
    }

    public Book getBook(long id) {
        return bookRepository.findById(id).get();
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public void save(Book book) throws BookStoreException {
        bookRepository.save(book);
    }

    public void updateBook(long id, String name, int pageNumber) throws BookStoreException {
        Book book = getBook(Long.valueOf(id));
        if (book == null)
            throw new BookStoreException("3001", "Book not found!");
        if (name != null)
            book.setName(name);
        if (pageNumber > 0)
            book.setPageNumber(pageNumber);
        save(book);
    }
}
