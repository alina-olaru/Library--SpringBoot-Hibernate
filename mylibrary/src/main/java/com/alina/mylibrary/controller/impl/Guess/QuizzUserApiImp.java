package com.alina.mylibrary.controller.impl.Guess;

import com.alina.mylibrary.controller.Interfaces.Guess.QuizzUserApi;
import com.alina.mylibrary.model.db.Quizz;
import com.alina.mylibrary.model.db.QuizzUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Admin.QuizzService;
import com.alina.mylibrary.service.impl.Guess.QuizzUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class QuizzUserApiImp implements QuizzUserApi {

    @Autowired
     QuizzService quizzService;

    @Autowired
     QuizzUserService quizzUserService;


    @Override
    public ApiResponse<List<Quizz>> getallQuizzez() {
      try{
          return new  ApiResponse<List<Quizz>>(ApiResponseType.SUCCESS,this.quizzService.getQuizzes(),"s-au adus datele cu succes");
      }catch(Exception ex){
          return new  ApiResponse<List<Quizz>>(ApiResponseType.ERROR,null,ex.getMessage() + ex.getSuppressed() + ex.getCause()+ ex.getStackTrace());
      }
    }

    @Override
    public ApiResponse<List<Quizz>> getQuizzeForUser(Integer userId) {
        try{
            return new  ApiResponse<List<Quizz>>(ApiResponseType.SUCCESS,this.quizzUserService.getQuizzForUser(userId),"s-au adus datele cu succes");
        }catch(Exception ex){
            return new  ApiResponse<List<Quizz>>(ApiResponseType.ERROR,null,ex.getMessage() + ex.getSuppressed() + ex.getCause()+ ex.getStackTrace());
        }
    }

    @Override
    public ApiResponse<QuizzUser> addQuizz(@RequestBody QuizzUser quizzUser, @RequestParam String response) {
        try{
            return new ApiResponse<QuizzUser>(ApiResponseType.SUCCESS,this.quizzUserService.add(quizzUser,response),"s-a inserat cu succes");
        }catch(Exception ex){
            return new ApiResponse<QuizzUser>(ApiResponseType.ERROR,null,ex.getMessage() + ex.getSuppressed() + ex.getCause()+ ex.getStackTrace());
        }
    }
}
