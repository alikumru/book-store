package com.manage.bookstore.book;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BookService {

    private BookRepository bookRepository;

    private BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    private void createBooks() {
        bookRepository.save(new Book("Suç ve Ceza"));
        bookRepository.save(new Book("Aşk-ı Memnu"));
        bookRepository.save(new Book("Yaprak Dökümü"));
        bookRepository.save(new Book("Beyaz Diş"));
        bookRepository.save(new Book("Kuyucaklı Yusuf"));
        bookRepository.save(new Book("Olasılıksız"));
        bookRepository.save(new Book("Empati"));
        bookRepository.save(new Book("Savaş Ve Barış"));

    }

    public Book getBook(long id) {
        return bookRepository.findById(id).get();
    }
}
