package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.repository.BookRepository;
import com.alina.mylibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public Book addBook(Book book) {
      //we're building the app with the idea that one book can be found in the pages more time(same title,author...but different year,cover,..something)
        //all the books with same title with whom you want to introduce.
        List<Book> books=bookRepository.findByBookTitle(book.getBookTitle());
        for(Book bookWithSameTitle:books){
            if(bookWithSameTitle.equals(book)){
                //the book is allready inserted,you can only edit it for increasing the numbers of volumes
                return null;
            }
            else{
                //you are able to insert the book
                this.bookRepository.save(book);
                return book;
            }
        }
        return null;
    }

    @Override
    public Book updateBook(Book book) {
     if(book!=null) {
         this.bookRepository.save(book);
         return book;
     }
     return null;
    }

    @Override
    public boolean deleteBook(int bookId) {
        this.bookRepository.deleteById(bookId);
        return true;
    }

    @Override
    public List<Book> getBook() {
       return this.bookRepository.findAll();
    }
}
