package com.manage.bookstore.book;

import com.manage.bookstore.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "BOOKS")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", nullable = false)
    private Long id;
    private String name;
    private int pageNumber;
    private int stock;
    @Column(columnDefinition="Decimal(10,2)")
    private double price;

    public Book(String name, int pageNumber, int stock, double price) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.stock = stock;
        this.price = price;
    }
}
