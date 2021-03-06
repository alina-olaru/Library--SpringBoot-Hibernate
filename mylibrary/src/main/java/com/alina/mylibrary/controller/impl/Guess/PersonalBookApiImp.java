package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PersonalBookApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PersonalBook;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.PersonalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonalBookApiImp implements PersonalBookApi {

    @Autowired
    PersonalBookService personalBookService;


    @Override
    public ApiResponse<PersonalBook> addBook(PersonalBook personalBook) {
        try {
            return new ApiResponse<PersonalBook>(ApiResponseType.SUCCESS, this.personalBookService.addBook(personalBook));
        }catch (Exception ex){
            return new ApiResponse<PersonalBook>(ApiResponseType.ERROR, null,"A aparut o eroare "+ex.getMessage() + "Add personal Book");

        }
    }

    @Override
    public ApiResponse<List<PersonalBook>> getMyBooks(Integer type, Integer userId) {
       List<PersonalBook> response=new ArrayList<>();
       try{
           response=this.personalBookService.getMyBooks(type,userId);
           return new ApiResponse<List<PersonalBook>>(ApiResponseType.SUCCESS,response);

       }catch (DBExceptions e){
           return new  ApiResponse<List<PersonalBook>>(ApiResponseType.ERROR,null,"a aparut o eroare in getMyBooks");

       }catch(InvalidParameterException e){
           return new  ApiResponse<List<PersonalBook>>(ApiResponseType.ERROR,null,"a aparut o eroare in getMyBooks"+e.getMessage());
       }
       catch(Exception e){
           return new  ApiResponse<List<PersonalBook>>(ApiResponseType.ERROR,null,"a aparut o eroare in getMyBooks"+e.getMessage());
       }
    }

    @Override
    public ApiResponse<Boolean> checkIfPersonal(Integer bookId, Integer userId) {
        Boolean response=false;
        try{
            response = this.personalBookService.checkIfIsMyBook(userId , bookId);
            return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
        }catch (DBExceptions ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,response,"DBExceptions prinsa");

        }catch (Exception ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,response,"Exception prinsa");
        }
    }

    @Override
    public ApiResponse<Boolean> deletePers(Integer bookId, Integer userId) {
        Boolean response=false;
        try{
            response = this.personalBookService.DeletePers(userId,bookId);
            return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response,"s-a sters cu succes");
        }catch (Exception ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,false,"nu s-a putut sterge,a aparut o eroare" + ex.getMessage() + ex.getLocalizedMessage());

        }
    }
}
