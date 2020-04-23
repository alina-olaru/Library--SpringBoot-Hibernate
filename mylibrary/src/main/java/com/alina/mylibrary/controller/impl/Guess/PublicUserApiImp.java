package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PublicUserApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public class PublicUserApiImp implements PublicUserApi {

    @Autowired
    BookUserService bookUserService;



    @Override
    public ApiResponse<BookUser>  GetById(int id) {
        //todo dc nu merge daca totusi e post?
        return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,this.bookUserService.GetUserByuserId(id));
    }

//    @DeleteMapping(path = "/{id}")
//    ApiResponse<Boolean> deletePublisher(@PathVariable int id);
}
