package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.Interfaces.Guest.PublicBookDao;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.repository.Guest.PublicBookRepository;
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
