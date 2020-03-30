package com.alina.mylibrary.service;


import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public Book addBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(int bookId);
    public List<Book> getBook();
}
