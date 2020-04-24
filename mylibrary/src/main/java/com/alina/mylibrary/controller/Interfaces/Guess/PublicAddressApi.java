package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.Address;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.Complaint;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/users/address")
@CrossOrigin
public interface PublicAddressApi {

    @PostMapping
    ApiResponse<BookUser> addAdress(@RequestBody BookUser bookUser, Address address);
}
