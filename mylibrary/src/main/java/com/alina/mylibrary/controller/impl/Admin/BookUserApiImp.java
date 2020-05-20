package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Admin.BookUserApi;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;

@Component
public class BookUserApiImp implements BookUserApi {


    /**
     *
     *
     *<p>Rest api for users(only CRUD operations ) from admin interfaces</p>
     * @see <a href="http://localhost:4200/admin/useri"></a>
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
    public ApiResponse<List<Address>> getAddressesForUser(@PathVariable int id) {
       return new  ApiResponse<List<Address>>(ApiResponseType.SUCCESS,this.bookUserService.getAddress(id));
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

    @Override
    public ApiResponse<HashSet<Book>> recoomandForUser(Integer userId) {
        try{
            return new ApiResponse<HashSet<Book>>(ApiResponseType.SUCCESS , this.bookUserService.recoomandForUser(userId) , "s-au adus datele cu succes");
        }catch (Exception ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR , null, "A intervenit o eroare" + " "+ ex.getStackTrace() + " " + ex.getClass() + " " + ex.getMessage() + " " + ex.getCause() + " " + ex.getLocalizedMessage());
        }
    }


}
