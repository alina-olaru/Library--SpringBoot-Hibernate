package com.alina.mylibrary.service.Interfaces.Guess;


import com.alina.mylibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublicBookService {

    List<Book> getBooksSF();
}