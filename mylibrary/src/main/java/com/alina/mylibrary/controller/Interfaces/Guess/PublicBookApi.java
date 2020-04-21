package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.Complaint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/api/landing/book")
@CrossOrigin
public interface PublicBookApi {

    @GetMapping("/SF")
    ApiResponse<List<Book>> getBooksSF();
}
