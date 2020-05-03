package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Wishlist;

import java.util.List;

public interface WishListDao {

    List<Wishlist> getAllWhislists();

    Wishlist getWishlistById(int wishlistId);
    Boolean deleteWishlist(int wishlistId) throws DaoException;
    Wishlist addWishlist(Wishlist wishlist) throws DaoException;


}
