package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.PublicAddressApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.Address;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.requests.UserAddressAdd;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;


@Component
public class PublicAddressApiImp implements PublicAddressApi {


    @Autowired
    BookUserService bookUserService;


    @Override
    public ApiResponse<BookUser> addAddress(@RequestBody UserAddressAdd user) {
        BookUser response=null;

        try{
            response=this.bookUserService.addAddress(user.getAddress()
                    , user.getUser());
            if(response!=null){
                return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,response,"Adresa s-a adaugat cu succes!");
            }
        }catch(NotFoundException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,response,e.getMessage());

        }
        catch (NullPointerException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,response,e.getMessage());

        }
        catch(FieldException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,response,e.getMessage());

        }
        catch (Exception e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,response,e.getMessage());

        }

        return new ApiResponse<BookUser>(ApiResponseType.ERROR,response,"Nu s-a putut adauga adresa.Ne pare rau!");

    }
};
