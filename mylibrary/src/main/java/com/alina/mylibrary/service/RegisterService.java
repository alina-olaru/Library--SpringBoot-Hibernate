package com.alina.mylibrary.service;

import com.alina.mylibrary.model.BookUser;
import org.springframework.stereotype.Service;

public interface RegisterService {
    public BookUser registerUser(BookUser bookUser);
}
