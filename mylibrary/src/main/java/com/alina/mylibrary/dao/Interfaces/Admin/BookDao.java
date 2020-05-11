package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    public List<Book> getBooks();
    public Book getBookbyId(int bookId);
    public List<Book> getBookbyAuthor(String firstName,String lastname);
    public Book addBook(Book book);
    public boolean deleteBook(int bookId);
    public Book updateBook(Book book);
    public  List<Book> getBooksByQuery(@Param("title") String title, @Param("count") int count)  throws DaoException;

}
