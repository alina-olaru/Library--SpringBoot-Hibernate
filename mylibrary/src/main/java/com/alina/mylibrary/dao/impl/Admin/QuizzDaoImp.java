package com.alina.mylibrary.dao.impl.Admin;


import com.alina.mylibrary.dao.Interfaces.Admin.QuizzDao;
import com.alina.mylibrary.model.Quizz;
import com.alina.mylibrary.repository.Admin.QuizzRepository;
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

    @Override
    public Boolean deleteQquizz(int quizzId) {
        if(quizzId>=1) {
            this.quizzRepository.deleteById(quizzId);
            return true;
        }
        return false;
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Quizz addQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);

    }
}
