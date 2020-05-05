package com.alina.mylibrary.controller.Interfaces.Admin;


import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@CrossOrigin
public interface BookApi {


    @PostMapping
    ApiResponse<Book> insertBook(@RequestBody Book book);


    @GetMapping
    ApiResponse<List<Book>> getBooks();


    @PutMapping(path = "/{id}")
    ApiResponse<Book> updateBook(@RequestBody Book book);


    @DeleteMapping(path = "/{id}")
    ApiResponse<Boolean> deleteBook(@PathVariable int id);
}


