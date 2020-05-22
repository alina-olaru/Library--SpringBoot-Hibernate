package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.MailMe;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Guess.MailMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class MailMeApiImp implements MailMe {

    @Autowired
    MailMeService mailMeService;

    @Autowired
    BookUserService bookUserService;

    @Override
    public ApiResponse<MailWhenInStoc> add(String mail, Integer book) {

        BookUser u=new BookUser();
        u.setFirstName("test");


        MailWhenInStoc user = new MailWhenInStoc();
        user.setBookmailme(book);
        user.setEmailformail(mail);
        try {
            return new ApiResponse<MailWhenInStoc>(ApiResponseType.SUCCESS, this.mailMeService.addMe(user), "s-a inserat cu success");
        }catch (Exception ex){
            return new ApiResponse<MailWhenInStoc>(ApiResponseType.ERROR,null,  ex.getCause() + "  "+ ex.getMessage() +"  "+ ex.getClass() + "  " + ex.getStackTrace());

        }
    }
}
