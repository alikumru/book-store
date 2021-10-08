package com.manage.bookstore.stock;

import com.manage.bookstore.book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "BOOK_STOCK")
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK")
    private Book book;

    private int stock;

    public Stock(Book book, int stock) {
        this.book = book;
        this.stock = stock;
    }
}
