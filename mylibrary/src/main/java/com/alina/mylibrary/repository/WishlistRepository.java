package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.PersonalBook;
import com.alina.mylibrary.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    /*
     List<BookUser> findByEmailAdress(String emailAdress);
    List<BookUser> findByUsername(String username);
     */

    //todo de implementat toate astea pt dashboard + populat cu date pt grafice

    List<Wishlist> findByuserwishlist(BookUser userwishlist);
    List<Wishlist> findBybookwishlist(Book bookwishlist);

}

