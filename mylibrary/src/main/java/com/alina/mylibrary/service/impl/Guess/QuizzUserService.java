package com.alina.mylibrary.service.impl.Guess;


import com.alina.mylibrary.exception.ServiceException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.RepositoryException;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Quizz;
import com.alina.mylibrary.model.db.QuizzUser;
import com.alina.mylibrary.repository.Admin.BookUserRepository;
import com.alina.mylibrary.repository.Guest.QuizzUserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizzUserService {

    @Autowired
    private QuizzUserRepository quizzUserRepository;

    @Autowired
    private BookUserRepository bookUserRepository;

    @Transactional
    public QuizzUser add(QuizzUser quizzUser,String response) throws ServiceException,Exception, NotFoundException,ClassNotFoundException, RepositoryException,DBExceptions {
        try {
            if (quizzUser.getUserForQuizz() == null) {
                throw new NullPointerException();
            }
            if (quizzUser.getQuizzForUser() == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex){
            throw new DBExceptions(ex.getMessage() + ex.getLocalizedMessage() + ex.getStackTrace(), 404, this.getClass().getName(), "personalBook obj", "add");
        }
        try{
           QuizzUser resp= this.quizzUserRepository.save(quizzUser);
            if((response.toLowerCase()==quizzUser.getQuizzForUser().getQuizzCorrectAnswer().toLowerCase()) || (response.toLowerCase().equals(quizzUser.getQuizzForUser().getQuizzCorrectAnswer().toLowerCase())))
            {
                BookUser user = this.bookUserRepository.findById(quizzUser.getUserForQuizz().getUserId()).orElse(null);
                if(user!=null) {
                    double bonus = user.getBonus() == null ? 0 : user.getBonus();
                    user.setBonus(bonus + quizzUser.getQuizzForUser().getBonus());
                    this.bookUserRepository.save(user);
                    return resp;
                } else
                {
                    throw new NullPointerException();
                }

            }
        }catch (Exception ex){
            throw new ServiceException();
        }
        return null;
    }


    public List<Quizz> getQuizzForUser(Integer userId) throws ServiceException,Exception, NotFoundException,ClassNotFoundException, RepositoryException {
        List<QuizzUser> all=new ArrayList<>();
        try{
        all = this.quizzUserRepository.findAll();
        }catch (Exception ex){
            throw new ServiceException();
        }
        List<Quizz> response=new ArrayList<>();
        try {
            for (QuizzUser q : all) {
                if (q.getUserForQuizz().getUserId() == userId) {
                    response.add(q.getQuizzForUser());

                }
            }
        }catch (OutOfMemoryError e){
            throw new ServiceException();
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ServiceException();
        }catch (IndexOutOfBoundsException e){
            throw new ServiceException();
        }

        return response;
    }
}
