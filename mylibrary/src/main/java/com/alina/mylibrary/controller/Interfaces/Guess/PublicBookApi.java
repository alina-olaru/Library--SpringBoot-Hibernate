package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.Complaint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/landing/book")
@CrossOrigin
public interface PublicBookApi {

//    @GetMapping("/SF")
//    ApiResponse<List<Book>> getBooksSF();

    @PostMapping("/category")
    ApiResponse<List<Book>> getBooks(@RequestParam String category);

    @GetMapping("/test")
    ApiResponse<List<Book>> test(@RequestParam String title);


}
