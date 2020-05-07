package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.WishListDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.repository.Guest.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Boolean deleteWishlist(int wishlistId) throws DaoException {
        if(wishlistId==0){
            throw new DaoException(4);
        }
        if(this.wishlistRepository.findById(wishlistId).equals(null)){
            throw new DaoException(4);
        }

        try {

            this.wishlistRepository.deleteById(wishlistId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) throws DaoException {
        if(wishlist.equals(null)){
            throw new DaoException(1);
        }
        try{
            this.wishlistRepository.save(wishlist);
            return wishlist;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<Book> getBooksWishForUser(Integer id) {
        List<Wishlist> wishlists=this.wishlistRepository.findAll();
        List<Book> response=new ArrayList<>();
        for(Wishlist w :wishlists){
            if(w.getUserwishlist().getUserId()==id){
                response.add(w.getBookwishlist());
            }
        }
        return response;
    }


}
