package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.dao.Interfaces.Guest.ReviewDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Review;
import com.alina.mylibrary.service.Interfaces.Guess.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    BookUserDao bookUserDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    ReviewDao reviewDao;


    @Override
    public List<Review> getReviewsForBook(Integer bookId) throws DBExceptions {

        Book book=null;
        try{
             book=this.bookDao.getBookbyId(bookId);
             if(book==null){
                 throw new DBExceptions("Obiect gol", 400, this.getClass().getName(), "Review obj", "get(for checking)");

             }
             return  this.reviewDao.getReviewsForBook(bookId);
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "Review obj", "get(for checking)");

        }


    }

    @Override
    public List<Review> getReviewsForUser(Integer userId) throws DBExceptions {
       BookUser user=null;
        try{
            user=this.bookUserDao.getBookUserById(userId);
            if(user==null){
                throw new DBExceptions("Obiect gol", 400, this.getClass().getName(), "Review obj", "get(for checking)");

            }
            return  this.reviewDao.getReviewsForUser(userId);
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "Review obj", "get(for checking)");

        }

    }

    @Override
    @Transactional
    public Review addReview(Review review) throws DBExceptions,FieldException {
        if(review==null){
            throw new DBExceptions("Obiect gol", 400, this.getClass().getName(), "Review obj", "get(for checking)");

        }
        if(review.getBookR()==null){
            throw new DBExceptions("Obiect gol", 400, this.getClass().getName(), "Review obj", "get(for checking)");

        }
        if((review.getReviewerName()==null)||(review.getTextReview().length()<10)){
            throw new FieldException("Campuri goale", "review", review.getClass().getName());

        }
        try{
          Review response=  this.reviewDao.addReview(review);

            Book book = this.bookDao.getBookbyId(review.getBookR().getBookId());

            int nrReviews = book.getNumberOfReviews();
            if(nrReviews == 0){
                book.setBookRating(review.getValue());
                book.setNumberOfReviews(1);
            } else
            {
                book.setBookRating( (book.getBookRating() * nrReviews + review.getValue()) / (nrReviews+1));
                book.setNumberOfReviews(nrReviews+1);
            }
            this.bookDao.updateBook(book);

            return response;

        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "Review obj", "insert");

        }
    }
}
