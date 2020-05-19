package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.ReviewApiPUBLIC;
import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewApiPUBLICImp implements ReviewApiPUBLIC {

    @Autowired
    ReviewService reviewService;


    @Override
    public ApiResponse<Review> addReview(Review review) {
        try {
            return new ApiResponse<Review>(ApiResponseType.SUCCESS, this.reviewService.addReview(review), "s-au adus datele cu succes");
        } catch (Exception ex) {
            return new ApiResponse<Review>(ApiResponseType.ERROR, null, ex.getMessage());

        }
    }
}