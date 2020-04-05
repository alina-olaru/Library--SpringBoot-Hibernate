package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@CrossOrigin
public interface AuthorApi {

    @PostMapping
    ApiResponse<Author> insertAuthor(@RequestBody Author author);


    @GetMapping
    ApiResponse<List<Author>>  getAuthors();


    @PutMapping(path="/{id}")
    ApiResponse<Author> updateAuthor(@RequestBody Author author);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteAuthor(@PathVariable int id);
}



/*

@RestController
@RequestMapping("/public/register")
@CrossOrigin
public interface RegisterApi {

    @PostMapping("/do")
    ApiResponse<BookUser> registerUser(@RequestBody BookUser user);

}



 */