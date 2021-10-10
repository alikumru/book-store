package com.manage.bookstore.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(
            value = "SELECT * FROM ORDERS u WHERE u.USER_ID = :id",
            nativeQuery = true)
    List<Order> findOrdersByUser(@Param("id") Long id);

    @Query(
            value = "SELECT * FROM ORDERS u WHERE u.USER_ID = :id AND u.ORDER_DATE >= :since",
            nativeQuery = true)
    List<Order> findUserOrdersSince(@Param("id") Long id, @Param("since") Date since);

    @Query(
            value = "SELECT * FROM ORDERS u WHERE u.USER_ID = :id AND u.ORDER_DATE >= :since AND u.ORDER_DATE <= :to",
            nativeQuery = true)
    List<Order> findUserOrdersSinceTo(@Param("id") Long id, @Param("since") Date since, @Param("to") Date to);
}
