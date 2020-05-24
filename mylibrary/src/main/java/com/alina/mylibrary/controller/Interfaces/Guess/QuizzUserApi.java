package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.model.db.Quizz;
import com.alina.mylibrary.model.db.QuizzUser;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/public/api/quizz/user")
@CrossOrigin
public interface QuizzUserApi {

    @GetMapping("/get/all")
    public ApiResponse<List<Quizz>> getallQuizzez();

    @PostMapping("/get/user")
    public ApiResponse<List<Quizz>> getQuizzeForUser(@RequestParam Integer userId);

    @PostMapping
    public ApiResponse<QuizzUser> addQuizz(@RequestBody QuizzUser quizzUser, @RequestParam String response);
}
