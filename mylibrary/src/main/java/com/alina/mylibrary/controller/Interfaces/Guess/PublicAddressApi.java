package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.requests.UserAddressAdd;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/users/address")
@CrossOrigin
public interface PublicAddressApi {

    @PostMapping
    ApiResponse<BookUser> addAddress(@RequestBody UserAddressAdd user);
}
