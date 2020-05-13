package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.PublicAddressApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.requests.UserAddressAdd;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Guess.AddressService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Component
public class PublicAddressApiImp implements PublicAddressApi {


    @Autowired
    AddressService addressService;


    @Override
    public ApiResponse<BookUser> addAddress(@RequestParam Integer userId,@RequestBody Address address) {

        BookUser user=null;
        try {
           user=this.addressService.addAddress(address,userId);
            return new ApiResponse<BookUser>(ApiResponseType.SUCCESS, user);

        }catch (Exception ex) {
            return new ApiResponse<BookUser>(ApiResponseType.ERROR, null,ex.getMessage());
        }

        }
    };

