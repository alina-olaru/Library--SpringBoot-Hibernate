package com.alina.mylibrary.controller.impl;


import com.alina.mylibrary.repository.ConfirmationTokenMailRepository;
import com.alina.mylibrary.repository.UserMailRepository;
import com.alina.mylibrary.service.impl.EmailSenderService;
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
