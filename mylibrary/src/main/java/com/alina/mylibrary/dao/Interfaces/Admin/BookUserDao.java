package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.BookUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUserDao {
    public List<BookUser> getBookUsers();
    public BookUser getBookUserById (int bookUserId);
    public BookUser getBookUserByUsername (String username);
    public BookUser getBookUserByEmail (String email);
    public BookUser addBookUser(BookUser bookUser);
    public BookUser updateBookUser(BookUser bookUser);
    public Boolean removeBookUser(int bookUserId);
}
