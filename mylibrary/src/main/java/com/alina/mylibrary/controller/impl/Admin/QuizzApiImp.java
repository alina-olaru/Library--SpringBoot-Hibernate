package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Admin.QuizzApi;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Quizz;
import com.alina.mylibrary.service.Interfaces.Admin.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizzApiImp implements QuizzApi {

    @Autowired
    private QuizzService quizzService;



    @Override
    public ApiResponse<List<Quizz>> getQuizzes() {
       return new ApiResponse<List<Quizz>>(ApiResponseType.SUCCESS,this.quizzService.getQuizzes(),"s-au adus datele din baza");
    }

    @Override
    public ApiResponse<Boolean> deleteQquizz(int quizzId) {
       if(quizzId<1){
           return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Nu s-a putut sterge din baza de date.");
       }

       Boolean response=false;
       try{
           response=this.quizzService.deleteQquizz(quizzId);
       }
       catch(DBExceptions e){
           return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,e.getMessage());

       }
       return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response,"s-a sters cu succes din baza de date");
    }

    @Override
    public ApiResponse<Quizz> updateQuizz(Quizz quizz) {
        if(quizz==null){
            return new ApiResponse<Quizz>(ApiResponseType.ERROR,null,"nu s-a putut edita in baza de date");

        }
       Quizz response=null;
        try{
            response=this.quizzService.updateQuizz(quizz);
        }catch (DBExceptions e){
            return new ApiResponse<Quizz>(ApiResponseType.ERROR,null,e.getMessage());


        }
            return new ApiResponse<Quizz>(ApiResponseType.SUCCESS,response,"s-a editat in baza de date cu succes");

    }

    @Override
    public ApiResponse<Quizz> addQuizz(Quizz quizz) {
        if(quizz==null){
            return new ApiResponse<Quizz>(ApiResponseType.ERROR,null,"nu s-a putut adauga in baza de date");

        }
        Quizz response=null;
        try {
            this.quizzService.addQuizz(quizz);
        }
        catch (DBExceptions e){
            return new ApiResponse<Quizz>(ApiResponseType.ERROR,null,e.getMessage());

        }
        return new ApiResponse<Quizz>(ApiResponseType.SUCCESS,response,"s-a adaugat in baza de date cu succes");

    }

}
