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
       if(quizzId<1){
           return false;
       }
       else{
           this.quizzDao.deleteQquizz(quizzId);
           return true;
       }
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        if(quizz==null){
            return null;
        }

        List<Quizz> quizzez=this.quizzDao.getQuizzes();
        if(quizzez==null){
            //then this is the first quizz you introduce in database-->so it's fine
            return this.quizzDao.updateQuizz(quizz);
        }

        for(Quizz q:quizzez){
            if(q.equals(quizz)){
                return null;
            }
            return this.quizzDao.updateQuizz(quizz);

        }
    }

    @Override
    public Quizz addQuizz(Quizz quizz) {
        if(quizz==null){
            return null;
        }

        List<Quizz> quizzez=this.quizzDao.getQuizzes();
        if(quizzez==null){
            //then this is the first quizz you introduce in database-->so it's fine
            return this.quizzDao.updateQuizz(quizz);
        }

        for(Quizz q:quizzez){
            if(q.equals(quizz)){
                return null;
            }
            return this.quizzDao.updateQuizz(quizz);

        }
    }
}
