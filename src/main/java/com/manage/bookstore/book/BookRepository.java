package com.manage.bookstore.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(
            value = "SELECT * FROM BOOK u WHERE u.BOOK_ID = :id",
            nativeQuery = true)
    Book findStockByBookId(@Param("id") Long id);
}
