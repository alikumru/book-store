package com.manage.bookstore.book;

import com.manage.bookstore.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int quantity;

    @ManyToMany(mappedBy = "ordered_books")
    Set<Order> orders;

    public Book(String name) {
        this.name = name;
    }
}
