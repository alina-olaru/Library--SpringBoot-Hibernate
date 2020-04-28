package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Quizz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzDao {

    public List<Quizz> getQuizzes();
    public Boolean deleteQquizz(int quizzId)  throws DaoException;
    public Quizz updateQuizz(Quizz quizz)  throws DaoException;
    public Quizz addQuizz(Quizz quizz)  throws DaoException;
}
