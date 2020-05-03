package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksAuthorsDao {
    public List<BooksAuthors> getBooksAuthors();

    public List<BooksAuthors> getBooksAuthorsByAuthor(Author author);

    public List<BooksAuthors> getBooksAuthorsByBook(Book book);

    public BooksAuthors getBooksAuthorsById(int id);

    public BooksAuthors addBooksAuthors(BooksAuthors booksAuthors);

    public boolean delteBooksAuthors(BooksAuthors booksAuthors);

}
