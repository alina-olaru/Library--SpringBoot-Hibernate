package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private BookUserDao bookUserDao;
    @Override
    public BookUser registerUser(BookUser bookUser) throws FieldException {

        /*

        String message, String field, String className

         */

        if(bookUser.getUserId()<1){
            throw new FieldException("Id incorect","userId",this.getClass().getName());
        }
        if((bookUser.getIsEnabled()!=false&&(bookUser.getIsEnabled()!=true))){
            throw new FieldException("Nu se poate acorda acest privilegiu","isEnabled",this.getClass().getName());
        }
        if((bookUser.getBlocked()!=false&&(bookUser.getBlocked()!=true))){
            throw new FieldException("Nu se poate acorda acest privilegiu","isBlocked",this.getClass().getName());
        }
        if((bookUser.isAdminPrivilege()!=false&&(bookUser.isAdminPrivilege()!=true))){
            throw new FieldException("Nu se poate acorda acest privilegiu","adminPrivilege",this.getClass().getName());
        }
        if((bookUser.isNewsletter()!=false&&(bookUser.isNewsletter()!=true))){
            throw new FieldException("Nu poate exista aceasta stare la newsletter","Newsletter",this.getClass().getName());
        }
        if((bookUser.isUserPrivilege()!=false&&(bookUser.isUserPrivilege()!=true))){
            throw new FieldException("Nu se poate acorda acest privilegiu","userPrivilege",this.getClass().getName());
        }

        if(bookUser.getPhoneNumber().length()!=10){
            throw new FieldException("Numarul de telefon are 10 cifre.Reincercati","phoneNumber",this.getClass().getName());

        }
        List<BookUser> UsersWithSameUsername=null;
        List<BookUser> UsersWithSamePhoneNumber=null;
        List<BookUser> UsersWithSameEmail=null;
        List<BookUser> AllUsers=this.bookUserDao.getBookUsers();
        for(BookUser b:AllUsers){
            if(b.getPhoneNumber()==bookUser.getPhoneNumber()){
                throw new FieldException("Numarul de telefon trebuie sa fie asociat unui singur cont.","phoneNumber",this.getClass().getName());

            }

            if(b.getEmailAdress().toLowerCase()==bookUser.getEmailAdress().toLowerCase()){
                throw new FieldException("Adresa de mail trebuie sa fie asociata unui singur cont.","emailAddress",this.getClass().getName());

            }

            if(b.getUsername().toLowerCase()==bookUser.getUsername().toLowerCase()){
                throw new FieldException("Username-ul este asociat altui cont.Incercati altceva.","username",this.getClass().getName());

            }


        }


        if((bookUser.getFirstName().length()<=3)||((bookUser.getLastName().length()<=3))){
            throw new FieldException("Nume prea scurt.","firstName/lastName",this.getClass().getName());

        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(bookUser.getEmailAdress()).matches()){
            throw  new FieldException("Adresa de mail invalida","emailAddress",this.getClass().getName());
        }

        if(DBCheck.isValidEmailAddress(bookUser.getEmailAdress())==false){
            throw  new FieldException("Adresa de mail invalida","emailAddress",this.getClass().getName());
        }

        if(DBCheck.isValidPhoneNumber(bookUser.getPhoneNumber())==false){
            throw  new FieldException("Nr. telefon invalid","phoneNumber",this.getClass().getName());
        }


        bookUser.setFirstName(DBCheck.Stringtify(bookUser.getFirstName()));
        bookUser.setLastName(DBCheck.Stringtify(bookUser.getLastName()));
       BookUser user = this.bookUserDao.addBookUser(bookUser);
       user.setPassword(null);
       return user;
    }
}
