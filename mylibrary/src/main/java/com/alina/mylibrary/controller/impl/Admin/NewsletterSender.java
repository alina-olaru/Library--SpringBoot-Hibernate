package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.impl.Admin.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsletterSender {

    /**
     *
     *
     *<p>Weekly newsletter sender service</p>
     * <p>For users that confirm that (field is true)</p>
     * since 2.5.0
     */

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    BookUserService bookUserService;

    /**
     *
     * <h1>Using cron to send the mail every monday at 13:45</h1>
     */
    @Scheduled(cron = "0 45 13 ? * MON")
    public void create() {
        final LocalDateTime start = LocalDateTime.now();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        List<BookUser> users=bookUserService.getUsers();
        List<BookUser> usersNews=new ArrayList<>();
        for(BookUser b:users){
            if((b.getBlocked()==false)&&(b.getIsEnabled()==true)&&(b.isNewsletter()==true)){
                usersNews.add(b);
            }
        }

        //TODO ADD HTML IN MAIL
        simpleMailMessage.setFrom("olarualina01@gmail.com");
        simpleMailMessage.setSubject("Newsletter test");
        simpleMailMessage.setText("Multumim ca te-ai abonat la newsletter-ul nostru,il vei primi saptmanaal.Pt dezabonare,click mai jos");
        for(BookUser user:usersNews) {
            simpleMailMessage.setTo(user.getEmailAdress());
            emailSenderService.sendEmail(simpleMailMessage);

        }

    }
}

