package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.dao.QuizzDao;
import com.alina.mylibrary.model.Quizz;
import com.alina.mylibrary.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuizzServiceImp implements QuizzService {


    @Autowired
    QuizzDao quizzDao;


    @Override
    public List<Quizz> getQuizzes() {
        return this.quizzDao.getQuizzes();
    }

    @Override
    public Boolean deleteQquizz(int quizzId) {
        return null;
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        return null;
    }

    @Override
    public Quizz addQuizz(Quizz quizz) {
        return null;
    }
}
