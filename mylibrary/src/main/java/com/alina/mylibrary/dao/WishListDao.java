package com.alina.mylibrary.dao;

import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.Wishlist;

import java.util.List;

public interface WishListDao {

    List<Wishlist> getAllWhislists();
    List<Wishlist> getWhislistForaUser(BookUser bookUser);
    List<Wishlist> getWishlistForBookTitle(String bookTitle);
    List<Wishlist> getWishlistForBookPublisher(String bookPublisher);
    List<Wishlist> getWishlistForBookAuthor(String firstName,String lastName);
    Boolean deleteFromWhislist(int userId,int bookId);
}
