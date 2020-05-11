package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.service.Interfaces.Guess.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorServiceImp implements BookAuthorService {

    @Autowired
    BookAuthorService bookAuthorService;

    @Override
    public List<BooksAuthors> findBooksAuthorsByAuthorId(Author author) {
        return this.bookAuthorService.findBooksAuthorsByAuthorId(author);
    }

    @Override
    public List<BooksAuthors> findBooksAuthorsByBookId(Book book) {
        return this.bookAuthorService.findBooksAuthorsByBookId(book);
    }

    @Override
    public Boolean deleteByAuthor(Author author) {
       return this.bookAuthorService.deleteByAuthor(author);
    }
}
