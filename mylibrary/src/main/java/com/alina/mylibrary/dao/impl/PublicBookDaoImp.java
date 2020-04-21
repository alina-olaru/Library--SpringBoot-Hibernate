package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.Interfaces.Guess.PublicBookDao;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.repository.Guess.PublicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublicBookDaoImp implements PublicBookDao {

    @Autowired
    PublicBookRepository publicBookRepository;


    @Override
    public List<Book> getBooksSF() {
        return this.publicBookRepository.getBookSF();
    }
}
