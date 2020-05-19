package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao {

    List<Review> getReviewsForBook(Integer bookId) throws DaoException;
    List<Review> getReviewsForUser(Integer userId) throws DaoException;
    Review addReview(Review review) throws DaoException;

}
