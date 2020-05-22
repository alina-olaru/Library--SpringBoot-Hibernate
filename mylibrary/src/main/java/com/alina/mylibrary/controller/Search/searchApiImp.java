package com.alina.mylibrary.controller.Search;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Search.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;

@Component
public class searchApiImp implements searchApi {

    @Autowired
    public searchService searchService;

    @Override
    public ApiResponse<HashSet<Book>> search(String query) {
       try{
           return new ApiResponse<HashSet<Book>>(ApiResponseType.SUCCESS,this.searchService.search(query),"s-au adus rezultatele cu succes");
       }catch (Exception ex){
           return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,ex.getMessage() + ex.getStackTrace() + ex.getCause() + ex.getSuppressed());

       }
    }
}
