package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/public/api/review")
@CrossOrigin
public interface ReviewApi {

    @GetMapping("/book")
    ApiResponse<List<Review>> getReviewsForBook(@RequestParam Integer bookId);

    @GetMapping("/user")
    ApiResponse<List<Review>> getReviewsForUser(@RequestParam Integer userId);


}


//    List<Review> getReviewsForBook(Integer bookId)  throws DBExceptions;
//    List<Review> getReviewsForUser(Integer userId) throws DBExceptions;
//    Review addReview(Review review) throws DBExceptions, FieldException;