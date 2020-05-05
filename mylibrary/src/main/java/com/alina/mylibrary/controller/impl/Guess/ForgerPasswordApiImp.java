package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.ForgetPasswordApi;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PasswordResetToken;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.repository.Admin.UserMailRepository;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.impl.Admin.EmailSenderService;
import com.alina.mylibrary.service.impl.Guess.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@Component
public class ForgerPasswordApiImp implements ForgetPasswordApi {


    @Autowired
    BookUserService bookUserService;

    @Autowired
    UserMailRepository userMailRepository;


    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Override
    public ApiResponse<Boolean> resetPassword(String userEmail) {
        BookUser user = bookUserService.GetUserByUsernameOrEmail(userEmail);
        if (user == null) {
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Nu exista adresa in baza de date");
        }
        String token = UUID.randomUUID().toString();
        bookUserService.createPasswordResetTokenForUser(user, token);


        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmailAdress());
        simpleMailMessage.setSubject("Schimbare parola");
        simpleMailMessage.setFrom("olarualina01@gmail.com");
        simpleMailMessage.setText("Pentru a va schimba parola de pe site-ul nostru,va rugam sa dati click pe link-ul de mai jos: "
                + "http://localhost:4200/change-password?token="+token);
        //TODO ADD HTML IN MAIL


//TODO sa pun timp de valabilitate pt token

        emailSenderService.sendEmail(simpleMailMessage);


        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,
                null,"Ati primit mail pentru schimbarea adresei");
    }



    @Override
    public ApiResponse<Boolean> changePass(String pass,String token) {

        /**
         * With token ->We find current user
         * And we change the password to the new one
         * since v.4.0.0
         *
         *
         */
        BookUser user=this.passwordResetTokenService.getUserByToken(token);
        user.setPassword(pass);
        this.bookUserService.editUser(user);
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,null,"Parola a fost schimbata cu succes");
    }
}




