package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.RegisterApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.ConfirmationToken;
import com.alina.mylibrary.repository.ConfirmationTokenMailRepository;
import com.alina.mylibrary.repository.UserMailRepository;
import com.alina.mylibrary.service.RegisterService;
import com.alina.mylibrary.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RegisterApiImpl implements RegisterApi {

    @Autowired
    private RegisterService registerService;

    @Autowired
    UserMailRepository userMailRepository;


    @Autowired
    ConfirmationTokenMailRepository confirmationTokenMailRepository;


    @Autowired
    private EmailSenderService emailSenderService;



    @Override
    public ApiResponse<BookUser> registerUser(@RequestBody BookUser user) {
        if(user!=null) {

            BookUser existinguser = userMailRepository.findByemailAdressIgnoreCase(user.getEmailAdress());
            if (existinguser != null) {
                return new ApiResponse<BookUser>(ApiResponseType.ERROR, null, "Adresa de email este deja inregistrata in baza de date.Introduceti alta adresa daca doriti un cont nou.");

            }


        else{

            this.userMailRepository.save(user);
            ConfirmationToken confirmationToken=new ConfirmationToken(user);
            confirmationTokenMailRepository.save(confirmationToken);

            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmailAdress());
            simpleMailMessage.setSubject("Inregistrare librarie");
            simpleMailMessage.setFrom("olarualina01@gmail.com");
            simpleMailMessage.setText("Pentru a confirma inregistrarea pe site-ul nostru,va rugam sa dati click pe link-ul de mai jos: "
                    + "http://localhost:4200/confirm-account?token="+confirmationToken.getConfirmationToken());




        emailSenderService.sendEmail(simpleMailMessage);
                return new ApiResponse<BookUser>(ApiResponseType.SUCCESS, user, "S-a trimis mail-ul de confirmare");

        }


        }
        return new ApiResponse<BookUser>(ApiResponseType.ERROR,null,"User null");


    }
//nu aici ce e mai jos


    @Override
    public ApiResponse<BookUser> confirmUserAccount(String confirmationToken) {

        ConfirmationToken token=this.confirmationTokenMailRepository.findByConfirmationToken(confirmationToken);
        if(token!=null){
            BookUser user=this.userMailRepository.findByemailAdressIgnoreCase(token.getUser().getEmailAdress());
            user.setIsEnabled(true);



            BookUser response = this.registerService.registerUser(user);
            return new ApiResponse<BookUser>(ApiResponseType.SUCCESS, response);
        }
        //TODO mesaj eroare cu detalii

        return new ApiResponse<BookUser>(ApiResponseType.ERROR, null, "Contul nu a putut fi creat, verifica datele introduse!");
    }


};
