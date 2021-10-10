package com.manage.bookstore.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.manage.bookstore.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Set<OrderDetails> orderDetails;

    private float subtotal;
    private String Address;

    public Order(User user, Set<OrderDetails> orderDetails) {
        this.user = user;
        this.orderDetails = orderDetails;
    }
}
