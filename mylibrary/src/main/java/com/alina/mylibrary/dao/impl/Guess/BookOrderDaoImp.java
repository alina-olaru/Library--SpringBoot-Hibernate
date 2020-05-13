package com.alina.mylibrary.dao.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guest.BookOrderDao;
import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.repository.Guest.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookOrderDaoImp implements BookOrderDao {

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Override
    public List<BookOrder> getOrders() {
        return this.bookOrderRepository.findAll();
    }

    @Override
    public BookOrder getOrderById(Integer id) {
        return this.bookOrderRepository.getAllByOrderId(id);
    }

    @Override
    public BookOrder addOrder(BookOrder order) {
        return this.bookOrderRepository.save(order);
    }

    @Override
    public Boolean deleteOrder(BookOrder order) {
        this.bookOrderRepository.delete(order);
        return true;
    }
}
