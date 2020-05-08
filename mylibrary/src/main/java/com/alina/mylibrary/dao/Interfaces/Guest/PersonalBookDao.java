package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.model.db.PersonalBook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalBookDao {
    public PersonalBook addBook(PersonalBook personalBook);
    /**
     * type is a parameter for what kind of info request should provide
     * type 1 = books added to "my books" section from application ( books that provices more info like price , dimensions,etc)
     * type 2 = books added by user with a photo or details from his account
     *
     */
    public List<PersonalBook> getMyBooks(Integer type , Integer userId);
    Boolean checkIfIsMyBook(Integer userId,Integer bookId);
}
