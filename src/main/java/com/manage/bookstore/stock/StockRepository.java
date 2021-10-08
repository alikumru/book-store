package com.manage.bookstore.stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query(
            value = "SELECT * FROM BOOK_STOCK u WHERE u.book = :book",
            nativeQuery = true)
    Stock findStockByBookId(@Param("book") Long book);
}
