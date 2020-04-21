package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.BookUser;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {
    public BookUser registerUser(BookUser bookUser)  throws FieldException;
}
