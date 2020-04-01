package com.alina.mylibrary.dao.impl;


import com.alina.mylibrary.dao.QuizzDao;
import com.alina.mylibrary.model.Quizz;
import com.alina.mylibrary.repository.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizzDaoImp implements QuizzDao {


    @Autowired
    private QuizzRepository quizzRepository;


    @Override
    public List<Quizz> getQuizzes() {
        return this.quizzRepository.findAll();
    }




}
