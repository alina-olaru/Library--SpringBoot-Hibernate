package com.alina.mylibrary.repository.Guest;

import com.alina.mylibrary.exception.ServiceExceptions.RepositoryException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {


    //todo de implementat toate astea pt dashboard + populat cu date pt grafice

    List<Wishlist> findByuserwishlist(BookUser userwishlist) throws RepositoryException;
    List<Wishlist> findBybookwishlist(Book bookwishlist) throws RepositoryException;
    Boolean deleteByBookwishlistAndUserwishlist(Book book,BookUser bookUser) throws RepositoryException;
    Wishlist findByUserwishlistAndBookwishlist(BookUser bookUser,Book book);




}

