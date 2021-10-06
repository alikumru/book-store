package com.manage.bookstore.statistic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "STATISTICS")
@Getter
@Setter
public class Statistic {

    @Id
    @Column(name = "statistic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
