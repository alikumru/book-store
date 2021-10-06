package com.manage.bookstore.book;

import com.manage.bookstore.author.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "BOOKS")
@Getter
@Setter
public class Book {

    @Id
    @Column(name = "book_id", nullable = false)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Author> authors;


}
