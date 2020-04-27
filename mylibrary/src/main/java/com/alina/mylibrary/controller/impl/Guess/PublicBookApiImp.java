package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PublicBookApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import com.alina.mylibrary.service.Interfaces.Guess.PublicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PublicBookApiImp implements PublicBookApi {

    @Autowired
    BookService bookService;

    @Override
    public ApiResponse<List<Book>> getBooks() {
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,this.bookService.getBook(),"s-au adus cartile cu succes");

    }
}

