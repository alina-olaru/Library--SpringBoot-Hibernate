package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.dao.BookDao;
import com.alina.mylibrary.dao.BooksAuthorsDao;
import com.alina.mylibrary.dao.BooksCategoriesDao;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BooksAuthors;
import com.alina.mylibrary.model.BooksCategories;
import com.alina.mylibrary.repository.BookRepository;
import com.alina.mylibrary.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BooksAuthorsDao booksAuthorsDao;

    @Autowired
    private BooksCategoriesDao booksCategoriesDao;

    @Override
    public Book addBook(Book book) {
      //we're building the app with the idea that one book can be found in the pages more time(same title,author...but different year,cover,..something)
        //all the books with same title with whom you want to introduce.
        List<Book> books=this.bookDao.getBooks();
        for(Book bookWithSameTitle:books){
            if(bookWithSameTitle.equals(book)){
                //the book is allready inserted,you can only edit it for increasing the numbers of volumes
                return null;
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Book deepCopy = objectMapper
                    .readValue(objectMapper.writeValueAsString(book), Book.class);

            //you are able to insert the book
            book.getBooksCategories().clear();
            book.getBookAuthor().clear();
            this.bookDao.addBook(book);

            deepCopy.getBookAuthor().forEach(elem -> {
                elem.setBookId(book);
                this.booksAuthorsDao.addBooksAuthors(elem);
            });
            deepCopy.getBooksCategories().forEach(elem -> {
                elem.setBooksC(book);
                this.booksCategoriesDao.addBooksCategories(elem);
            });
            book.getBookAuthor().addAll(deepCopy.getBookAuthor());
            book.getBooksCategories().addAll(deepCopy.getBooksCategories());
            return book;
        } catch (Exception ex){
            return null;
        }

    }

    @Override
    public Book updateBook(Book book) {
     if(book!=null) {
         book.getBookAuthor().forEach(elem -> elem.setBookId(book));
         book.getBooksCategories().forEach(elem -> elem.setBooksC(book));
         this.bookDao.updateBook(book);
         return book;
     }
     return null;
    }

    @Override
    public boolean deleteBook(int bookId) {
        this.bookDao.deleteBook(bookId);
        return true;
    }

    @Override
    public List<Book> getBook() {
       return this.bookDao.getBooks();
    }
}
