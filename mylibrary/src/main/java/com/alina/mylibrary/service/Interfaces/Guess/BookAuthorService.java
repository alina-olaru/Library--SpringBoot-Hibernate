package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookAuthorService {
    List<BooksAuthors> findBooksAuthorsByAuthorId(Author author);
    List<BooksAuthors> findBooksAuthorsByBookId(Book book);
    Boolean deleteByAuthor(Author author);
}
