package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.model.db.BookOrder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookOrderService {
    List<BookOrder> getOrders();
    BookOrder getOrderById(Integer id);
    BookOrder addOrder(BookOrder order);
    Boolean deleteOrder(BookOrder order);
}
