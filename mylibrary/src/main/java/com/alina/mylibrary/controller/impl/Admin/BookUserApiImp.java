package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Admin.BookUserApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookUserApiImp implements BookUserApi {


    /**
     *
     *
     *<p>Rest api for users(only CRUD operations ) from admin interfaces</p>
     * @ seet <a href="http://localhost:4200/admin/useri"></a>
     * since 1.0.0
     */



    @Autowired
    BookUserService bookUserService;



    @Override
    public ApiResponse<BookUser> insertUser(BookUser bookUser) {
        if(bookUser==null){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,null,"nu s-a putut adauga userul in baza de date");

        }
        return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,this.bookUserService.addUser(bookUser));


    }

    @Override
    public ApiResponse<List<BookUser>> getUser() {
        return new ApiResponse<List<BookUser>>(ApiResponseType.SUCCESS,this.bookUserService.getUsers(),"s-au adus datele cu succces");
    }

    @Override
    public ApiResponse<BookUser> updateUser(BookUser bookUser) {
        if(bookUser==null){
            return new ApiResponse<BookUser>(ApiResponseType.ERROR,null,"nu s-a putut adauga userul in baza de date");

        }
        return new ApiResponse<BookUser>(ApiResponseType.SUCCESS,this.bookUserService.editUser(bookUser));


    }

    @Override
    public ApiResponse<Boolean> deleteUser(int id) {
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,this.bookUserService.deleteUser(id));
    }


}
