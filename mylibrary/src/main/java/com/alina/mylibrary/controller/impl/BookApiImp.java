package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.BookApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.service.AuthorService;
import com.alina.mylibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Component
public class BookApiImp implements BookApi {

    @Autowired
    private BookService bookService;


    @Override

    public ApiResponse<Book> insertBook(Book book) {
       if(book!=null){

           Book response = this.bookService.addBook(book);
           return new ApiResponse<Book>(ApiResponseType.SUCCESS,response);
       }

       return new ApiResponse<Book>(ApiResponseType.ERROR,null,"Cartea nu poate fi adaugata");
    }

    @Override
    public ApiResponse<List<Book>> getBooks() {
        List<Book> response=this.bookService.getBook();
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,response);

    }

    @Override
    public ApiResponse<Book> updateBook(Book book) {
       if(book==null){
           return new ApiResponse<Book>(ApiResponseType.ERROR,null,"Cartea nu poate fi editata");
       }
       Book response=this.bookService.updateBook(book);
       return new ApiResponse<Book>(ApiResponseType.SUCCESS,response);
    }

    @Override
    public ApiResponse<Boolean> deleteBook(int id) {
      boolean response= this.bookService.deleteBook(id);
      return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
    }
}
