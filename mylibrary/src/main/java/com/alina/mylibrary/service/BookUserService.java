package com.alina.mylibrary.service;

import com.alina.mylibrary.model.BookUser;
import org.springframework.stereotype.Service;

@Service
public interface BookUserService {
    public BookUser GetUserByUsernameOrEmail(String searchKey);
}
