package com.alina.mylibrary.dao.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guest.BookAuthorDao;
import com.alina.mylibrary.dao.impl.Admin.BooksAuthorsDaoImpl;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.repository.Guest.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookAuthorDaoImp implements BookAuthorDao {

    @Autowired
    BookAuthorRepository bookAuthorRepository;

    @Override
    public List<BooksAuthors> findBooksAuthorsByAuthorId(Author author) {
        return this.bookAuthorRepository.findBooksAuthorsByAuthorId(author);
    }

    @Override
    public List<BooksAuthors> findBooksAuthorsByBookId(Book book) {
        return this.bookAuthorRepository.findBooksAuthorsByBookId(book);
    }

    @Override
    public Boolean deleteByAuthor(Author author) {
        try {
             this.bookAuthorRepository.deleteByAuthorId(author);
             return true;
        } catch (Exception ex) {

            return false;
        }
    }
}
