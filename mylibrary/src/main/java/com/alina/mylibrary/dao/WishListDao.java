package com.alina.mylibrary.dao;

import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.model.Wishlist;

import java.util.List;

public interface WishListDao {

    List<Wishlist> getAllWhislists();

    Wishlist getWishlistById(int wishlistId);

    //    List<Wishlist> getWhislistForaUser(BookUser bookUser);
//    List<Wishlist> getWhislistIncludingBookTitle(String bookTitle);
//    List<Wishlist> getWishlistForBookTitle(String bookTitle);
//    List<Wishlist> getWishlistForBookPublisher(String bookPublisher);
//    List<Wishlist> getWishlistForBookAuthor(Author author);
    //   Boolean deleteFromWhislist(BookUser bookUser, Book book);
    Boolean deleteWishlist(int wishlistId);
    Wishlist addWishlist(Wishlist wishlist);


}
