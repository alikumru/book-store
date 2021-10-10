package com.manage.bookstore.user;

import com.manage.bookstore.order.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(
            value = "SELECT * FROM USERS",
            nativeQuery = true)
    List<User> findAllUsers();

    @Query(
            value = "SELECT * FROM ORDERS u WHERE u.USER_ID = :id AND u.ORDER_DATE >= :since AND u.ORDER_DATE <= :to",
            nativeQuery = true)
    List<Order> findUserOrdersSinceTo(@Param("id") Long id, @Param("since") Date since, @Param("to") Date to);
}
