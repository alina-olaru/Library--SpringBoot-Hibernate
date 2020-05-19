package com.alina.mylibrary.dao.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Guest.ReviewDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.repository.Guest.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDaoImp implements ReviewDao {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsForBook(Integer bookId) throws DaoException {
        List<Review> response=new ArrayList<>();
        try {
            List<Review> all = this.reviewRepository.findAll();
            for (Review r : all) {
                if (r.getBookR().getBookId() == bookId) {
                    response.add(r);
                }
            }
            return response;
        }catch (Exception ex){
            throw new DaoException(3);
        }
    }

    @Override
    public List<Review> getReviewsForUser(Integer userId) throws DaoException {
        List<Review> response=new ArrayList<>();
        try {
            List<Review> all = this.reviewRepository.findAll();
            for (Review r : all) {
                if (r.getUserReviewMaker() != null) {
                    if (r.getUserReviewMaker().getUserId() == userId) {
                        response.add(r);
                    }
                }
            }
            return response;
        }catch (Exception ex){
            throw new DaoException(3);
        }
    }

    @Override
    public Review addReview(Review review) throws DaoException {
       try{
           return this.reviewRepository.save(review);
       }catch (Exception ex){
           throw new DaoException(1);
       }
    }
}
