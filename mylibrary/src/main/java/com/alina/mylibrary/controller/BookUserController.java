package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.*;
import com.alina.mylibrary.repository.BookUserRepository;
import com.alina.mylibrary.repository.PublisherRepository;
import com.alina.mylibrary.repository.QuizzRepository;
import com.alina.mylibrary.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/users")
public class BookUserController {

    @Autowired
    private BookUserRepository bookUserRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private VoucherRepository voucherRepository;

    public BookUserController(BookUserRepository bookUserRepository,
                              PublisherRepository publisherRepository,
                              QuizzRepository quizzRepository,
                              VoucherRepository voucherRepository) {
        this.bookUserRepository = bookUserRepository;
        this.publisherRepository = publisherRepository;
        this.quizzRepository = quizzRepository;
        this.voucherRepository = voucherRepository;
    }

    @GetMapping("/all")
    public Iterable<BookUser> all(){

        List<Quizz> q = quizzRepository.findAll();

        

//        Quizz newQuizz = new Quizz();
//        newQuizz.setQuizzQuestion("asdsa");
//        newQuizz.setNumberOfQuestions(2);
//        newQuizz.setQuizzAnswers("vfefv");
//        newQuizz.setQuizzCorrectAnswer("szcdcvdffcfv");
//
//        quizzRepository.save(newQuizz);
//
//        Voucher a = new Voucher();
//        a.setVoucherTitle("a");
//        a.setVoucherDescription("b");
//        a.setVoucherMaximumUses(5);
//        a.setVoucherPrice(5.5f);
//        a.setVoucherStartDate(new Date());
//        a.setVoucherEndDate(new Date());
//        a.setQuizzez(newQuizz);
//
//        Voucher b = new Voucher();
//        b.setVoucherTitle("q");
//        b.setVoucherDescription("z");
//        b.setVoucherMaximumUses(5);
//        b.setVoucherPrice(5.5f);
//        b.setVoucherStartDate(new Date());
//        b.setVoucherEndDate(new Date());
//        b.setQuizzez(newQuizz);
//
//        this.voucherRepository.save(a);
//        this.voucherRepository.save(b);






//        this.bookUserRepository.deleteAll();
//        BookUser b1=new BookUser(
//                "Alina",
//                "Olaru",
//                "olarualina01@gmail.com",
//                "0726080518",
//                true,
//                true,
//                true,
//                "Romania",
//                "Bacau",
//                "Bacau",
//                "Calea Moldovei",
//                179,
//                "Happy ",
//                2,
//                57,
//                "parola",
//                "Username",
//                new HashSet<PersonalBook>(),
//                new HashSet<Wishlist>(),
//                new HashSet<BookOrder>(),
//                new HashSet<Complaint>(),
//                new HashSet<Review>(),
//                new HashSet<VoucherUser>()
//        );
//
//       this.bookUserRepository.save(b1);
//        this.publisherRepository.save(new Publisher(
//                "Test"
//        ));
        return this.bookUserRepository.findAll();
    }
}
