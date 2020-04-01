package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/books")
@CrossOrigin
public interface BookApi {


    @PostMapping
    ApiResponse<Book> insertBook(@RequestBody Book book);


    @GetMapping
    ApiResponse<List<Book>> getBooks();


    @PutMapping(path="/{id}")
    ApiResponse<Book> updateBook(@RequestBody Book book);



    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteBook(@PathVariable int id);
}
