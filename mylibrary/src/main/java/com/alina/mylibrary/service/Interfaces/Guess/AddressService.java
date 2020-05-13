package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.BookUser;
import org.springframework.stereotype.Service;


@Service
public interface AddressService {
    BookUser addAddress(Address address, Integer userId) throws DBExceptions;
    Boolean deleteAddress(Integer id) throws DBExceptions;
}
