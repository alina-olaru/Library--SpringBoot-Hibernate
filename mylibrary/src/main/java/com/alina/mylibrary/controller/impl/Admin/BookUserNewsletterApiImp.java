package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.BookUserNewsletterApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookUserNewsletterApiImp implements BookUserNewsletterApi {



    @Autowired
    BookUserService bookUserService;

    @Override
    public ApiResponse<BookUser> YesToNewsletter(int id) {
        BookUser result=null;
        try{
            result= this.bookUserService.yesNewsletter(id);
            if(result!=null){
                return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,result,"Userul a fost abonat la newsletter");
            }
        }
        catch(NotFoundException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Userul cu id-ul dat nu a putut fi gasit in baza de date");
        }
        catch(FieldException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Userul cu id-ul dat este deja abonat la newsletter");

        }
        return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Datele userului cu id-ul dat nu au putut fi modificate");

    }

    @Override
    public ApiResponse<BookUser> NoToNewsletter(int id) {
        BookUser result=null;
        try{
            result= this.bookUserService.NoNewsletter(id);
            if(result!=null){
                return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,result,"Userul a fost dezabonat de la newsletter");
            }
        }
        catch(NotFoundException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Userul cu id-ul dat nu a putut fi gasit in baza de date");
        }
        catch(FieldException e){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Userul cu id-ul dat nu este abonat la newsletter");

        }
        return new ApiResponse<BookUser>(ApiResponseType.ERROR,result,"Datele userului cu id-ul dat nu au putut fi modificate");
    }


}
