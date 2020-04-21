package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guess.PublicBookDao;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.service.Interfaces.PublicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class PublicBookServiceImp implements PublicBookService {


    @Autowired
    PublicBookDao publicBookDao;


    @Override
    public List<Book> getBooksSF() {
        return this.publicBookDao.getBooksSF();
    }
}
