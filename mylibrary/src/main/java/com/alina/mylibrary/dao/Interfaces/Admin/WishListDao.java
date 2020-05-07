package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface WishListDao {

    List<Wishlist> getAllWhislists();

    Wishlist getWishlistById(int wishlistId);
    Boolean deleteWishlist(int wishlistId) throws DaoException;
    Wishlist addWishlist(Wishlist wishlist) throws DaoException;
    List<Book> getBooksWishForUser(Integer id);




}
