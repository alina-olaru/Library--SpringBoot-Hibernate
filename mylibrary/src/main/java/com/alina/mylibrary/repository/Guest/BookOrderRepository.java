package com.alina.mylibrary.repository.Guest;


import com.alina.mylibrary.model.db.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {


    BookOrder getAllByOrderId(Integer orderId);
}
