package com.alina.mylibrary.controller.Interfaces.Admin;


import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Quizz;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/Quizz")
@CrossOrigin
public interface QuizzApi {


    @GetMapping
    public ApiResponse<List<Quizz>> getQuizzes();

    @DeleteMapping(path = "/{quizzId}")
    public ApiResponse<Boolean> deleteQquizz(@PathVariable int quizzId);

    @PutMapping
    public ApiResponse<Quizz> updateQuizz(@RequestBody Quizz quizz);

    @PostMapping
    public ApiResponse<Quizz> addQuizz(@RequestBody Quizz quizz);
}
