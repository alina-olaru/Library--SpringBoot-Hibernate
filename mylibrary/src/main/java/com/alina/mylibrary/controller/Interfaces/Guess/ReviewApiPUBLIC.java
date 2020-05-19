package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/review")
@CrossOrigin
public interface ReviewApiPUBLIC {
    @PostMapping
    ApiResponse<Review> addReview(@RequestBody Review review);
}
