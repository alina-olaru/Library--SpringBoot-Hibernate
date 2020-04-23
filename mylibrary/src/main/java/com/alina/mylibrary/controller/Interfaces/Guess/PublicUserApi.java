package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.BookUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/users")
@CrossOrigin
public interface PublicUserApi {

    @PostMapping(path="/{id}")
    ApiResponse<BookUser> GetById(@PathVariable int id);
}
