package com.alina.mylibrary.service;

import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.repository.BookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUserService {

    @Autowired
    private BookUserRepository bookUserRep;

    public void saveUser(BookUser us){
        bookUserRep.save(us);
    }
}
