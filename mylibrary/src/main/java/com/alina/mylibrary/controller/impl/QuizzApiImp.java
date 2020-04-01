package com.alina.mylibrary.controller.impl;


import com.alina.mylibrary.controller.QuizzApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Quizz;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizzApiImp implements QuizzApi {
    @Override
    public ApiResponse<List<Quizz>> getQuizzes() {
        return null;
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
