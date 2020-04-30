package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
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
    public List<Book> getBookByCategory(String category) throws DBExceptions;
    List<Book> getBooksByQuery(String title)  throws DBExceptions ,Exception;
}
