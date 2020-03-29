package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.RegisterApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RegisterApiImpl implements RegisterApi {

    @Autowired
    private RegisterService registerService;

    @Override
    public ApiResponse<BookUser> registerUser(@RequestBody BookUser user) {
        if(user!=null){
            BookUser response = this.registerService.registerUser(user);
            return new ApiResponse<BookUser>(ApiResponseType.SUCCESS, response);
        }
        //TODO mesaj eroare cu detalii
        return new ApiResponse<BookUser>(ApiResponseType.ERROR, null, "Contul nu a putut fi creat, verifica datele introduse!");
    }
}
