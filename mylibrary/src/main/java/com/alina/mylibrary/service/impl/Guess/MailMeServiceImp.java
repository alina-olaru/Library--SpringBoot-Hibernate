package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Guest.MailWhenInStocDao;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import com.alina.mylibrary.repository.Guest.MailWhenInStocRepository;
import com.alina.mylibrary.service.Interfaces.Guess.MailMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailMeServiceImp implements MailMeService {

    @Autowired
    MailWhenInStocDao mailWhenInStocDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    MailWhenInStocRepository mailWhenInStocRepository;

    @Override
    public List<MailWhenInStoc> GetBooksByBook(Book book) {
        return this.mailWhenInStocDao.GetBooksByBook(book);
    }

    @Override
    public MailWhenInStoc addMe(MailWhenInStoc user) {
        return this.mailWhenInStocDao.addMe(user);
    }

    @Override
    public List<String> sendMails(Integer bookId) {
        List<String> mails= new ArrayList<>();
        List<MailWhenInStoc> all =this.mailWhenInStocRepository.findAll();
        for(MailWhenInStoc m : all){
            if(m.getBookmailme()==bookId){
                mails.add(m.getEmailformail());
            }
        }
        return mails;
    }
}
