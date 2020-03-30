package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.BookDao;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.repository.AuthorRepository;
import com.alina.mylibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao {


    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookbyId(int bookId) {
        return this.bookRepository.findById(bookId).stream().findFirst().orElse(null);
    }

    @Override
    public  List<Book> getBookbyAuthor(String firstName, String lastname) {

        return new ArrayList<Book>();

    }

    //        return this.bookUserRepository.findByEmailAdress(email).stream().findFirst().orElse(null);
    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public boolean deleteBook(int bookId) {
        return false;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }
}
