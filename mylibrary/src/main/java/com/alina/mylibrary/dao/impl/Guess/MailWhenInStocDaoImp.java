package com.alina.mylibrary.dao.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guest.MailWhenInStocDao;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import com.alina.mylibrary.repository.Guest.MailWhenInStocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailWhenInStocDaoImp implements MailWhenInStocDao {

    @Autowired
    MailWhenInStocRepository mailWhenInStocRepository;

    @Override
    public List<MailWhenInStoc> GetBooksByBook(Book book) {
        return this.mailWhenInStocRepository.findAll();
    }

    @Override
    public MailWhenInStoc addMe(MailWhenInStoc user) {
        return this.mailWhenInStocRepository.save(user);
    }
}
