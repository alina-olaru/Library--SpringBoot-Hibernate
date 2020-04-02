package com.alina.mylibrary.service;

import com.alina.mylibrary.model.BookUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookUserService {


    public BookUser GetUserByUsernameOrEmail(String searchKey);
    public  List<BookUser> getUsers();
    public BookUser addUser(BookUser bookUser);
    public BookUser editUser(BookUser bookUser);
    public Boolean deleteUser(int bookUserId);
}
