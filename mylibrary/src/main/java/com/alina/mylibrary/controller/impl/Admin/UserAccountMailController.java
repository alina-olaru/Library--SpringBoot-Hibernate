package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.repository.Admin.ConfirmationTokenMailRepository;
import com.alina.mylibrary.repository.Admin.UserMailRepository;
import com.alina.mylibrary.service.impl.Admin.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAccountMailController {


    @Autowired
    UserMailRepository userMailRepository;


    @Autowired
    ConfirmationTokenMailRepository confirmationTokenMailRepository;


    @Autowired
    private EmailSenderService emailSenderService;
}
