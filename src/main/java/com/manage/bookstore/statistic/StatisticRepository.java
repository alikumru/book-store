package com.manage.bookstore.statistic;

import com.manage.bookstore.order.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository {

    @Query(
            value = "SELECT * FROM ORDERS u WHERE u.USER_ID = :id",
            nativeQuery = true)
    List<Order> findOrdersByUser(@Param("id") Long id);
}
