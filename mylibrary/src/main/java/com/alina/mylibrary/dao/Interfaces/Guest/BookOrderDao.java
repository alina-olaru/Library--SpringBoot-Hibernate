package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.model.db.BookOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookOrderDao {

    List<BookOrder> getOrders();
    BookOrder getOrderById(Integer id);
}
