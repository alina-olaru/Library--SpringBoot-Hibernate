package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<BookUser> getUsers() {
        return this.bookUserDao.getBookUsers();
    }

    @Override
    public BookUser addUser(BookUser bookUser) {
        if(bookUser==null){
            return null;
        }
        return this.bookUserDao.addBookUser(bookUser);
    }

    @Override
    public BookUser editUser(BookUser bookUser) {
        if(bookUser==null){
            return null;
        }
        return this.bookUserDao.updateBookUser(bookUser);
    }

    @Override
    public Boolean deleteUser(int id) {
        return this.bookUserDao.removeBookUser(id);
    }

    @Override
    public BookUser GetUserByuserId(Integer userId) {
        return this.bookUserDao.getBookUserById(userId);
    }
}
