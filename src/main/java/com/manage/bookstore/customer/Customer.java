package com.manage.bookstore.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "CUSTOMERS")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false)
    private Long id;
    private String name;
    private String lastname;

    /*@OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();*/

}
