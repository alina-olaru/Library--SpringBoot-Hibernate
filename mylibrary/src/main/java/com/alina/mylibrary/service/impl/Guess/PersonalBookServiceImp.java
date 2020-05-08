package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guest.PersonalBookDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.PersonalBook;
import com.alina.mylibrary.service.Interfaces.Guess.PersonalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonalBookServiceImp implements PersonalBookService {

    @Autowired
    PersonalBookDao personalBookDao;

    @Override
    public PersonalBook addBook(PersonalBook personalBook) {
        return this.personalBookDao.addBook(personalBook);
    }

    @Override
    public List<PersonalBook> getMyBooks(Integer type, Integer userId) throws DBExceptions {
//        (type==1||type==2) ?  return : throw new DBExceptions();
       int[] vars={1,2};
       boolean found= Arrays.stream(vars).anyMatch(x -> x == type);
       if(found==false){
           throw new InvalidParameterException("Nu exista aceasta optiune.Type este invalid");
       }
      return this.personalBookDao.getMyBooks(type,userId);
        }

    @Override
    public Boolean checkIfIsMyBook(Integer userId, Integer bookId) throws DBExceptions, Exception {
        //TODO find by id null ->eroare

        boolean response=false;
        try{
           response =  this.personalBookDao.checkIfIsMyBook(userId,bookId);
           return response;
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "personalBook obj", "get(for checking)");
        }
    }
}

