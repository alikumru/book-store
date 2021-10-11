package com.manage.bookstore.statistic;

import com.manage.bookstore.order.Order;
import com.manage.bookstore.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    @Query(
            value = "select MONTH,SUM(QUANTITY) as QUANTITY,SUM(PURCHASE)  as PURCHASE, Count(ORDER_COUNT) AS COUNT FROM(\n" +
                    "   select MONTHNAME(o.order_date) as MONTH,o.order_id AS ORDER_COUNT ,d.quantity AS QUANTITY , (d.quantity * b.price) AS PURCHASE from orders o\n" +
                    "  join users u ON u.user_id = o.user_id\n" +
                    "  JOIN orderdetails d ON o.order_id = d.order_id\n" +
                    "  JOIN books b ON d.book_id = b.book_id\n" +
                    " WHERE u.USER_ID= :user_id" +
                    ") group by MONTH",
            nativeQuery = true)
    List<Object> getMonthlyReport(@Param("user_id") Long user_id);
}
