package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Book;
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
    ApiResponse<List<Book>> test(@RequestParam String title, @RequestParam int count);

    @GetMapping(path="/details/{id}")
    ApiResponse<Book> getBookById(@PathVariable  int id);


}

