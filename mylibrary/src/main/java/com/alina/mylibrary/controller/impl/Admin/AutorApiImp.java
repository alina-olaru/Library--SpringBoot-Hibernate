package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.AuthorApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.service.Interfaces.Admin.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutorApiImp  implements AuthorApi {


    @Autowired
    private AuthorService authorService;

    @Override
    public ApiResponse<Author> insertAuthor(Author author) {
        if(author!=null){

            Author response=this.authorService.addAuthor(author);
            return new ApiResponse<Author>(ApiResponseType.SUCCESS,response);
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
        if(author!=null)
        {
            Author response=this.authorService.updateAuthor(author);
            return new ApiResponse<Author>(ApiResponseType.SUCCESS,author);
        }
        return new ApiResponse<Author>(ApiResponseType.ERROR,null,"Autorul nu a putut fi updatat");
    }

    @Override
    public ApiResponse<Boolean> deleteAuthor(int id) {

        boolean response=this.authorService.deleteAuthor(id);
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
    }
}