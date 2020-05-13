package com.alina.mylibrary.service.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Guest.BookOrderDao;
import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.service.Interfaces.Guess.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOrderServiceImp implements BookOrderService {

    @Autowired
    BookOrderDao bookOrderDao;

    @Override
    public List<BookOrder> getOrders() {
        return this.bookOrderDao.getOrders();
    }

    @Override
    public BookOrder getOrderById(Integer id) {
        return this.bookOrderDao.getOrderById(id);
    }
}
