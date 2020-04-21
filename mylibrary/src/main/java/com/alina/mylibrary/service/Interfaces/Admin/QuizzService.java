package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.model.Quizz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizzService {
    public List<Quizz> getQuizzes();
    public Boolean deleteQquizz(int quizzId);
    public Quizz updateQuizz(Quizz quizz);
    public Quizz addQuizz(Quizz quizz);

}
