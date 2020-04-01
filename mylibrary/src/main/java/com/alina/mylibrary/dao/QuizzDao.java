package com.alina.mylibrary.dao;


import com.alina.mylibrary.model.Quizz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzDao {

    public List<Quizz> getQuizzes();
    public Boolean deleteQquizz(int quizzId);
    public Quizz updateQuizz(Quizz quizz);
    public Quizz addQuizz(Quizz quizz);
}
