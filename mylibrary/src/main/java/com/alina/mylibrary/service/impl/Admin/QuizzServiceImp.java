package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.QuizzDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Quizz;
import com.alina.mylibrary.service.Interfaces.Admin.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizzServiceImp implements QuizzService {


    @Autowired
    QuizzDao quizzDao;


    @Override
    public List<Quizz> getQuizzes() {
        return this.quizzDao.getQuizzes();
    }

    @Override
    public Boolean deleteQquizz(int quizzId)  throws DBExceptions {
       if(quizzId<1){
           return false;
       }

       Boolean b=false;
     try {
       b= this.quizzDao.deleteQquizz(quizzId);
        if(b==true){
            return true;
        }
     }catch (DaoException e){
         throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "quizzr obj", "delete");

     }
     return b;
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) throws DBExceptions {
        if(quizz==null){
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "QUIZZ obj", "update");

        }

        List<Quizz> quizzez=this.quizzDao.getQuizzes();


        for(Quizz q:quizzez){
            if(q.equals(quizz)){
                throw new DBExceptions("Quizz deja inregistrat in baza de date", 400, this.getClass().getName(), "quizzr obj", "update");
            }


        }
        Quizz response=null;
        try{
            response= this.quizzDao.updateQuizz(quizz);
            if(!response.equals(null)){
                return response;
            }
        }catch (DaoException e){
            throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "quizzr obj", "update");

        }

        return response;
    }

    @Override
    public Quizz addQuizz(Quizz quizz) throws DBExceptions {
        if(quizz==null){
            throw new DBExceptions("Obiectul trimis este gol", 404, this.getClass().getName(), "quizzr obj", "Insert");

        }

        List<Quizz> quizzez=this.quizzDao.getQuizzes();

        for(Quizz q:quizzez){
            if(q.equals(quizz)){
                throw new DBExceptions("Quizz deja inregistrat in baza de date", 400, this.getClass().getName(), "quizzr obj", "Insert");
            }


        }
        Quizz response=null;
        try{
        response= this.quizzDao.addQuizz(quizz);
        if(response!=null){
            return response;
        }
        }catch (DaoException e){
            throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "quizzr obj", "Insert");

        }
        return response;
    }
}
