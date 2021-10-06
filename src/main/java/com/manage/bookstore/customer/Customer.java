package com.manage.bookstore.customer;

import com.manage.bookstore.author.Author;
import com.manage.bookstore.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CUSTOMERS")
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false)
    private Long id;
    private String name;
    private String lastname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Order> orders;

}
