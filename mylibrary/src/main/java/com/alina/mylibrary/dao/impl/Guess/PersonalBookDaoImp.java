package com.alina.mylibrary.dao.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guest.PersonalBookDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PersonalBook;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.repository.Admin.BookRepository;
import com.alina.mylibrary.repository.Guest.PersonalBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonalBookDaoImp implements PersonalBookDao {

    @Autowired
    PersonalBookRepository personalBookRepository;



    @Override
    public PersonalBook addBook(PersonalBook personalBook) {
        return this.personalBookRepository.save(personalBook);
    }

    @Override
    public List<PersonalBook> getMyBooks(Integer type, Integer userId)
    {
        List<PersonalBook> allMyBooks=this.personalBookRepository.findAll();
        List<PersonalBook> response=new ArrayList<>();
        if(type==2){

            for(PersonalBook p:allMyBooks){

                    if (p.getBook().getBookId() == 27) {
                        response.add(p);

                }
            }
        }
        if(type==1){


            for(PersonalBook p:allMyBooks){
                if(p.getBook().getBookId()!=27){
                    response.add(p);
                }
            }
        }
        return response;
    }

    @Override
    public Boolean checkIfIsMyBook(Integer userId, Integer bookId) {
      List<PersonalBook> w=this.personalBookRepository.findAll();
        for (PersonalBook w1 : w) {
            if ((w1.getUser().getUserId() == userId) && (w1.getBook().getBookId() == bookId)) {
                return true;
            }
        }
     return false;
    }

    @Override
    public Boolean deletePers(BookUser bookUser, Book book) throws DaoException {
        try {
            return this.personalBookRepository.deleteByBookAndUser(book,bookUser);
        }catch (Exception e){
            return false;
        }
    }
}
