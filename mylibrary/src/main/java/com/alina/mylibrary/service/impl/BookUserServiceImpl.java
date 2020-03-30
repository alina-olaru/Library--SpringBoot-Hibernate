package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.dao.BookUserDao;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUserServiceImpl implements BookUserService {

    @Autowired
    private BookUserDao bookUserDao;

    @Override
    public BookUser GetUserByUsernameOrEmail(String searchKey) {
        var user = this.bookUserDao.getBookUserByUsername(searchKey);
        if (user == null) {
            user = this.bookUserDao.getBookUserByEmail(searchKey);
        }
        if(user!=null){
            user.setPassword(null);
        }
        return user;
    }
}
