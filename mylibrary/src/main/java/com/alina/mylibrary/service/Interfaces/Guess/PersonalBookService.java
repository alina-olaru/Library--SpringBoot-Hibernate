package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.PersonalBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalBookService {
    PersonalBook addBook(PersonalBook personalBook);
    public List<PersonalBook> getMyBooks(Integer type, Integer userId) throws DBExceptions;
    Boolean checkIfIsMyBook(Integer userId,Integer bookId) throws DBExceptions,Exception;
}
