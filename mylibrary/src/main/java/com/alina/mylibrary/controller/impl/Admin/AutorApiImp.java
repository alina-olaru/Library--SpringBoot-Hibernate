package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.AuthorApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.service.Interfaces.Admin.AuthorService;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutorApiImp  implements AuthorApi {

    /**
     *
     *
     *<p>Rest api for authors(only CRUD operations ) from admin interfaces</p>
     *@see <a href="http://localhost:4200/admin/autori"></a>
     * since 1.0.0
     */
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Override
    public ApiResponse<Author> insertAuthor(Author author) {
        if(author!=null){
            Author response=null;
            try {
                 response = this.authorService.addAuthor(author);
                 if(response!=null) {
                     return new ApiResponse<Author>(ApiResponseType.SUCCESS, response);
                 }
            }
            catch (FieldException e){
                return new ApiResponse<Author>(ApiResponseType.ERROR,response,e.getMessage());
            }
        }

        return new ApiResponse<Author>(ApiResponseType.ERROR,null,"Autorul nu a putut fi adaugat");
    }


    @Override
    public ApiResponse<List<Author>> getAuthors() {
        List<Author> response=this.authorService.getAuthors();
        return new ApiResponse<List<Author>>(ApiResponseType.SUCCESS,response);
    }

    @Override
    public ApiResponse<Author> updateAuthor(Author author) {
        if(author!=null){
            Author response=null;
            try {
                response = this.authorService.updateAuthor(author);
                if(response!=null) {
                    return new ApiResponse<Author>(ApiResponseType.SUCCESS, response);
                }
                else{
                    return new ApiResponse<Author>(ApiResponseType.ERROR,null,"Autorul nu a putut fi editat");

                }
            }
            catch (FieldException e){
                return new ApiResponse<Author>(ApiResponseType.ERROR,response,e.getMessage());
            }
        }

        return new ApiResponse<Author>(ApiResponseType.ERROR,null,"Autorul nu a putut fi editat");
    }


    @Override
    public ApiResponse<Boolean> deleteAuthor(int id) {


        Boolean response=false;
        try {
            response = this.authorService.deleteAuthor(id);
        }catch (Exception e){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,e.getMessage());
        }
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
    }




    }
