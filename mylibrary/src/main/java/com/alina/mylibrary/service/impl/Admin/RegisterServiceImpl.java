package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private BookUserDao bookUserDao;
    @Override
    public BookUser registerUser(BookUser bookUser) {
       BookUser user = this.bookUserDao.addBookUser(bookUser);
       user.setPassword(null);
       return user;
    }
}
