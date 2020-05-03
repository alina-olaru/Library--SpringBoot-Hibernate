package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.PublicBookApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PublicBookApiImp implements PublicBookApi {

    @Autowired
    BookService bookService;


    @Override
    public ApiResponse<List<Book>> getBooks(String category) {
        List<Book> result=null;
        try{
            result=this.bookService.getBookByCategory(category);
        }
        catch (DBExceptions e){
            return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,null,e.getMessage());
        }
        catch (Exception e){
            return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,null,e.getMessage());
        }
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,result,"s-au adus cartile cu succes");
    }

    @Override
    public ApiResponse<List<Book>> test(String title) {

        List<Book>response = null;
        try {
            response=this.bookService.getBooksByQuery(title);
            if(response.size()>0){
                return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,response,"Carti aduse cu succes dupa categorie");
            }
        }catch (DBExceptions e){
            return new ApiResponse<List<Book>>(ApiResponseType.ERROR,null,e.message);
        }catch (Exception e){
            return new ApiResponse<List<Book>>(ApiResponseType.ERROR,null,e.getMessage());
        }
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,response,"totul e bine");
    }


}

