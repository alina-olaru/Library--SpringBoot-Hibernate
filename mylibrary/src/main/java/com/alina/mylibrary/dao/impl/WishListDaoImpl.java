package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.WishListDao;
import com.alina.mylibrary.model.*;
import com.alina.mylibrary.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WishListDaoImpl implements WishListDao {

    @Autowired
    WishlistRepository wishlistRepository;


    @Override
    public List<Wishlist> getAllWhislists() {
        return this.wishlistRepository.findAll();
    }

    @Override
    public Wishlist getWishlistById(int wishlistId) {
        return this.wishlistRepository.findById(wishlistId).orElse(null);
    }

    @Override
    public Boolean deleteWishlist(int wishlistId) {
        try {

            this.wishlistRepository.deleteById(wishlistId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        try{
            this.wishlistRepository.save(wishlist);
            return wishlist;
        }catch (Exception ex){
            return null;
        }
    }


}
