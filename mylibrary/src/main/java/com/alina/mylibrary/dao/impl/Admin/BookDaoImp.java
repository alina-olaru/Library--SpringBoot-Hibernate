package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.repository.Admin.AuthorRepository;
import com.alina.mylibrary.repository.Admin.BookRepository;
import com.alina.mylibrary.repository.Custom.AuthorCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class BookDaoImp implements BookDao {


    @Autowired
    private BookRepository bookRepository;




    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getBooks() {
        var books = this.bookRepository.findAll();
        return books;
    }

    @Override
    public Book getBookbyId(int bookId) {
        var book = this.bookRepository.findById(bookId).stream().findFirst().orElse(null);
        return book;
    }

    @Override
    public List<Book> getBookbyAuthor(String firstName, String lastname) {

        List<Book> books=new ArrayList<>();
        List<Book> response=new ArrayList<>();

        books=this.bookRepository.findAll();
        for(Book b:books){
            for(BooksAuthors ba : b.getBookAuthor()){
                if(((ba.getAuthorId().getFirstName().equals(firstName))&&(ba.getAuthorId().getLastName().equals(lastname)))||
                        ((ba.getAuthorId().getFirstName().equals(lastname))&&(ba.getAuthorId().getLastName().equals(firstName)))){

                    response.add(b);

                }



            }
        }
        return response;

    }

    //        return this.bookUserRepository.findByEmailAdress(email).stream().findFirst().orElse(null);
    @Override
    public Book addBook(Book book) {

        try {
            if (book != null) {
//                var bookAuthors = book.getBookAuthor();
//                var bookCategories = book.getBooksCategories();
//                 book.getBooksCategories().clear();
//                 book.getBookAuthor().clear();
                this.bookRepository.save(book);
//                book.setBooksCategories(bookCategories);
//                book.setBookAuthor(bookAuthors);
//                book.getBooksCategories().forEach(elem -> elem.setBooksC(book));
//                book.getBookAuthor().forEach(elem -> elem.setBookId(book));
                //            this.bookRepository.save(book);
                return book;
            }
        } catch (Exception ex) {
            this.bookRepository.deleteById(book.getBookId());
        }
        return null;
    }

    @Override
    public boolean deleteBook(int bookId) {
        if (bookId == 0) {
            return false;
        }

        this.bookRepository.deleteById(bookId);
        return true;

    }

    @Override
    public Book updateBook(Book book) {
        if (book.getBookId() == 0) {
            return null;
        }
        this.bookRepository.save(book);
        return book;

    }

    @Override
    public List<Book> getBooksByQuery(String title, int count) throws DaoException {
        try {
            var books = this.bookRepository.getBooksByQuery(title, PageRequest.of(0,count));
            return books;
        }catch (Exception e){
            throw new DaoException(3);
        }
    }
    }

