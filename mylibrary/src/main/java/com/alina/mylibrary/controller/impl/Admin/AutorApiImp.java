package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.AuthorApi;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.service.Interfaces.Admin.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutorApiImp  implements AuthorApi {

    /**
     *
     *
     *<p>Rest api for authors(only CRUD operations ) from admin interfaces</p>
     * @ seet <a href="http://localhost:4200/admin/autori"></a>
     * since 1.0.0
     */
    @Autowired
    private AuthorService authorService;

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

        boolean response=this.authorService.deleteAuthor(id);
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
    }
}