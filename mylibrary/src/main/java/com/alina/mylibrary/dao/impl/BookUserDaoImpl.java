package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.BookUserDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.repository.BookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookUserDaoImpl implements BookUserDao {

    @Autowired
    private BookUserRepository bookUserRepository;

    @Override
    public List<BookUser> getBookUsers() {
        return this.bookUserRepository.findAll();
    }

    @Override
    public BookUser getBookUserById(int bookUserId) {
        return this.bookUserRepository.findById(bookUserId).orElse(null);
    }

    @Override
    public BookUser getBookUserByUsername(String username) {
        return this.bookUserRepository.findByUsername(username).stream().findFirst().orElse(null);
    }

    @Override
    public BookUser getBookUserByEmail(String email) {
        return this.bookUserRepository.findByEmailAdress(email).stream().findFirst().orElse(null);
    }

    @Override
    public BookUser addBookUser(BookUser bookUser) {
        this.bookUserRepository.save(bookUser);
        return bookUser;
    }

    @Override
    public BookUser updateBookUser(BookUser bookUser) {
        if (bookUser.getUserId() == 0) {
            return null;
        }
        this.bookUserRepository.save(bookUser);
        return bookUser;
    }

    @Override
    public Boolean removeBookUser(int bookUserId) {
        if (bookUserId == 0) {
            return false;
        }
        this.bookUserRepository.deleteById(bookUserId);
        return true;
    }
}
