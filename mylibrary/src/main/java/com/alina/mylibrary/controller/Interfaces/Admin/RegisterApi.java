package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.BookUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/register")
@CrossOrigin
public interface RegisterApi {

    @PostMapping("/do")
    ApiResponse<BookUser> registerUser(@RequestBody BookUser user);

    @PostMapping(value="/confirm-account")
    public ApiResponse<BookUser> confirmUserAccount(@RequestBody String confirmationToken);

}
