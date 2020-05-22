package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.db.MailWhenInStoc;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mailme")
@CrossOrigin
public interface MailMe
{
    @PostMapping
    ApiResponse<MailWhenInStoc> add(@RequestParam String mail , @RequestParam Integer book);
}
