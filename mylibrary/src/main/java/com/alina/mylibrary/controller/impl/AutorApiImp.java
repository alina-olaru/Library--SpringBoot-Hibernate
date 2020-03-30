package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.AuthorApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}



/*


    @Autowired
    private RegisterService registerService;

    @Override
    public ApiResponse<BookUser> registerUser(@RequestBody BookUser user) {
        if(user!=null){
            BookUser response = this.registerService.registerUser(user);
            return new ApiResponse<BookUser>(ApiResponseType.SUCCESS, response);
        }
        //TODO mesaj eroare cu detalii
        return new ApiResponse<BookUser>(ApiResponseType.ERROR, null, "Contul nu a putut fi creat, verifica datele introduse!");
    }
}


 */