package com.alina.mylibrary.service.Interfaces.Guess;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    List<Review> getReviewsForBook(Integer bookId)  throws DBExceptions;
    List<Review> getReviewsForUser(Integer userId) throws DBExceptions;
    Review addReview(Review review) throws DBExceptions, FieldException;
}
