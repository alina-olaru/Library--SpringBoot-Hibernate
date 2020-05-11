package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookAuthorDao {

    List<BooksAuthors> findBooksAuthorsByAuthorId(Author author);
    List<BooksAuthors> findBooksAuthorsByBookId(Book book);
    Boolean deleteByAuthor(Author author);
}
