package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Quizz;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/admin/Quizz")
@CrossOrigin
public interface QuizzApi {


    @GetMapping
    public ApiResponse<List<Quizz>> getQuizzes();

    @DeleteMapping
    public ApiResponse<Boolean> deleteQquizz(int quizzId);

    @PutMapping
    public ApiResponse<Quizz> updateQuizz(Quizz quizz);

    @PostMapping
    public ApiResponse<Quizz> addQuizz(Quizz quizz);
}
