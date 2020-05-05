package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public Book addBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(int bookId);
    public List<Book> getBook();
    public List<Book> getBookByCategory(String category) throws DBExceptions;
    List<Book> getBooksByQuery(String title, int count)  throws DBExceptions ,Exception;
    public List<Book> deleteBookByAuthor(Author author) throws DBExceptions ,Exception;

}
