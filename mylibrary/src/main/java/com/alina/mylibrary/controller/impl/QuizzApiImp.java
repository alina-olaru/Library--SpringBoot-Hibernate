package com.alina.mylibrary.controller.impl;


import com.alina.mylibrary.controller.QuizzApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Quizz;
import com.alina.mylibrary.service.QuizzService;
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
        return null;
    }

    @Override
    public ApiResponse<Quizz> updateQuizz(Quizz quizz) {
        return null;
    }

    @Override
    public ApiResponse<Quizz> addQuizz(Quizz quizz) {
        return null;
    }
}
