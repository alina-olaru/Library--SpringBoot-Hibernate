package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Guess.BookOrderService;
import com.alina.mylibrary.service.impl.Admin.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("public/api")
@CrossOrigin
public class ConfirmOrderSender {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    BookUserService bookUserService;

    @Autowired
    BookOrderService bookOrderService;


    @GetMapping("/order/mail/{orderId}")
    public ApiResponse<Boolean> sendMailOrder(@PathVariable Integer orderId){

        try {
            final LocalDateTime start = LocalDateTime.now();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //TODO ADD HTML IN MAIL
            simpleMailMessage.setFrom("olarualina01@gmail.com");
            simpleMailMessage.setSubject("Confirmare comanda");
            simpleMailMessage.setText("Multumim pentru comanda facuta.O poti vedea in sectiunea 'istoric comenzi' din contul tau!Vei primi un mail cu factura.");
            BookOrder order = this.bookOrderService.getOrderById(orderId);
            BookUser user = order.getOrdersUser();
            simpleMailMessage.setTo(user.getEmailAdress());
            emailSenderService.sendEmail(simpleMailMessage);
            return new ApiResponse<Boolean>(ApiResponseType.SUCCESS, null);
        }catch (Exception ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,null);

        }
    }

    public ApiResponse<Boolean> sendInvoice(@PathVariable Integer orderId){

        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,null);

    }

}
