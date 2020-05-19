package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.ReviewApi;
import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewApiImp implements ReviewApi {

    @Autowired
    ReviewService reviewService;


    @Override
    public ApiResponse<List<Review>> getReviewsForBook(Integer bookId) {
        try{
            return new ApiResponse<List<Review>>(ApiResponseType.SUCCESS,this.reviewService.getReviewsForBook(bookId),"s-au adus datele cu succes");
        }catch (Exception ex){
            return new ApiResponse<List<Review>>(ApiResponseType.ERROR,null,ex.getMessage());

        }
    }

    @Override
    public ApiResponse<List<Review>> getReviewsForUser(Integer userId) {
        try{
            return new ApiResponse<List<Review>>(ApiResponseType.SUCCESS,this.reviewService.getReviewsForUser(userId),"s-au adus datele cu succes");
        }catch (Exception ex){
            return new ApiResponse<List<Review>>(ApiResponseType.ERROR,null,ex.getMessage());

        }
    }


}
