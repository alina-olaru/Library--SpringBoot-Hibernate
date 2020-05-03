package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BooksAuthorsDao;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.repository.Admin.BooksAuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksAuthorsDaoImpl implements BooksAuthorsDao {

    @Autowired
    private BooksAuthorsRepository booksAuthorsRepository;

    @Override
    public List<BooksAuthors> getBooksAuthors() {
        return this.booksAuthorsRepository.findAll();
    }

    @Override
    public List<BooksAuthors> getBooksAuthorsByAuthor(Author author) {
        return this.booksAuthorsRepository.findByAuthorId(author);
    }

    @Override
    public List<BooksAuthors> getBooksAuthorsByBook(Book book) {
        return this.booksAuthorsRepository.findByBookId(book);
    }

    @Override
    public BooksAuthors getBooksAuthorsById(int id) {
        return this.booksAuthorsRepository.findById(id).orElse(null);
    }

    @Override
    public BooksAuthors addBooksAuthors(BooksAuthors booksAuthors) {
        if(booksAuthors != null){
            this.booksAuthorsRepository.save(booksAuthors);
            return booksAuthors;
        }
        return null;
    }

    @Override
    public boolean delteBooksAuthors(BooksAuthors booksAuthors) {
        if(booksAuthors != null){
            this.booksAuthorsRepository.delete(booksAuthors);
            return true;
        }
        return false;
    }

}
