package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/api/user/password")
@CrossOrigin
public interface ForgetPasswordApi {


    @PostMapping("sendMail")
    public ApiResponse<Boolean> resetPassword(@RequestParam("email") String userEmail);


    @PostMapping("change-password")
    public ApiResponse<Boolean> changePass(@RequestParam("password") String pass,@RequestParam("token") String token);
}
