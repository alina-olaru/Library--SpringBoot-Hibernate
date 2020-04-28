package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.BookApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookApiImp implements BookApi {

    /**
     *
     *
     *<p>Rest api for books(only CRUD operations ) from admin interfaces</p>
     * @ seet <a href="http://localhost:4200/admin/carti"></a>
     * since 1.0.0
     */

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
    public ApiResponse<List<Book>> getBooks()
    {
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
