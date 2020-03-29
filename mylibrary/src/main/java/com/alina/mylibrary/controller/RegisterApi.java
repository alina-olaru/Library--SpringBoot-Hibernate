package com.alina.mylibrary.controller;

import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.BookUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/register")
@CrossOrigin
public interface RegisterApi {

    @PostMapping("/do")
    ApiResponse<BookUser> registerUser(@RequestBody BookUser user);

}
