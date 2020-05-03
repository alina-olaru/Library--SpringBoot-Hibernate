package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PublicVoucherApi;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.PublicVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class PublicVoucherApiImp implements PublicVoucherApi {

    @Autowired
    PublicVoucherService publicVoucherService;



    @Override
    public ApiResponse<List<String>> getLanguages() {
        return new ApiResponse<List<String>>(ApiResponseType.SUCCESS , this.publicVoucherService.getLanguages(),"Am adus limbile din baza de date");
    }
}
