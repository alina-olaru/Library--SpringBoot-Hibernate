package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PublicBookApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BooksCategories;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import com.alina.mylibrary.service.Interfaces.PublicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class PublicBookApiImp implements PublicBookApi {

    @Autowired
    PublicBookService publicBookService;




    @Override
    public ApiResponse<List<Book>> getBooksSF() {
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,this.publicBookService.getBooksSF(),"s-au adus cartile cu succes");
    }
}

