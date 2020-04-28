package com.alina.mylibrary.dao.impl.Admin;


import com.alina.mylibrary.dao.Interfaces.Admin.QuizzDao;
import com.alina.mylibrary.exception.DaoException;
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
    public Boolean deleteQquizz(int quizzId)  throws DaoException  {
      if(this.quizzRepository.findById(quizzId).equals(null)){
          throw new DaoException(4);
      }
      this.quizzRepository.deleteById(quizzId);
      return true;


    }

    @Override
    public Quizz updateQuizz(Quizz quizz)  throws DaoException {

        List<Quizz> quizzes=this.quizzRepository.findAll();
        for(Quizz q:quizzes){
            if(q.equals(quizz)){
                throw new DaoException(2);
            }
        }
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Quizz addQuizz(Quizz quizz)  throws DaoException{
        List<Quizz> quizzes=this.quizzRepository.findAll();
        for(Quizz q:quizzes){
            if(q.equals(quizz)){
                throw new DaoException(1);
            }
        }
        return this.quizzRepository.save(quizz);

    }
}
