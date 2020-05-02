package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.repository.Admin.AuthorRepository;
import com.alina.mylibrary.repository.Admin.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        return this.bookRepository.findById(bookId).stream().findFirst().orElse(null);
    }

    @Override
    public  List<Book> getBookbyAuthor(String firstName, String lastname) {

        return new ArrayList<Book>();

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
        }catch (Exception ex) {
            this.bookRepository.deleteById(book.getBookId());
        }
        return null;
    }

    @Override
    public boolean deleteBook(int bookId)
    {
        if(bookId==0){
            return false;
        }

        this.bookRepository.deleteById(bookId);
        return true;

    }

    @Override
    public Book updateBook(Book book)
    {
       if(book.getBookId()==0){
           return null;
       }
       this.bookRepository.save(book);
       return book;
    }

    @Override
    public List<Book> getBooksByQuery(String title) throws DaoException {
//        try {
//            return this.bookRepository.getBooksByQuery(title);
//        }catch (Exception e){
//            throw new DaoException(3);
//        }

        return new ArrayList<>();
    }
}
