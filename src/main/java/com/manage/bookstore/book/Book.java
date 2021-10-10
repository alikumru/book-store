package com.manage.bookstore.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.manage.bookstore.order.Order;
import com.manage.bookstore.order.OrderDetails;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    @JsonBackReference
    private Set<OrderDetails> orderDetails;

    public Book(String name, int pageNumber, int stock, double price) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.stock = stock;
        this.price = price;
    }
}
