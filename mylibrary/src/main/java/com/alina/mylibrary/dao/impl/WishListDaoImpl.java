package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.WishListDao;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.Wishlist;
import com.alina.mylibrary.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WishListDaoImpl implements WishListDao {

    @Autowired
    WishlistRepository wishlistRepository;


    @Override
    public List<Wishlist> getWhislistIncludingBookTitle(String bookTitle) {
       if(bookTitle.length()<2){
           return null;
       }
       List<Wishlist> wishLists=this.wishlistRepository.findAll();
       List<Wishlist> response=null;
       for(Wishlist w:wishLists){
           if(w.getBookwishlist().getBookTitle().equals(bookTitle)){
               response.add(w);
           }
       }

       return response;
    }

    @Override
    public List<Wishlist> getAllWhislists() {
        return this.wishlistRepository.findAll();
    }

    @Override
    public List<Wishlist> getWhislistForaUser(BookUser bookUser) {
        return this.wishlistRepository.findByuserwishlist(bookUser);
    }

    @Override
    public List<Wishlist> getWishlistForBookTitle(String bookTitle) {
        return null;
    }

    @Override
    public List<Wishlist> getWishlistForBookPublisher(String bookPublisher) {
        if(bookPublisher==null){
            return null;
        }
        List<Wishlist> wishLists=this.wishlistRepository.findAll();
        List<Wishlist> response=null;
        for(Wishlist w:wishLists){
            if(w.getBookwishlist().getPublisher().getPublisherTitle()==bookPublisher){
                response.add(w);
            }
        }

        return response;
    }

    @Override
    public List<Wishlist> getWishlistForBookAuthor(String firstName, String lastName) {
        return null;
    }

    @Override
    public Boolean deleteFromWhislist(int userId, int bookId) {
        return null;
    }
}
