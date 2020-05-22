package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.WishListDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.repository.Custom.WishlistCustomRepository;
import com.alina.mylibrary.repository.Guest.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class WishListDaoImpl implements WishListDao {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    WishlistCustomRepository wishlistCustomRepository;


    @Override
    public List<Wishlist> getAllWhislists() {

        return this.wishlistRepository.findAll();
    }

    @Override
    public Wishlist getWishlistById(int wishlistId) {
        return this.wishlistRepository.findById(wishlistId).orElse(null);
    }

    @Override
    public Boolean deleteWishlist(BookUser bookUser,Book book) throws DaoException {
        try {
            return this.wishlistRepository.deleteByBookwishlistAndUserwishlist(book,bookUser);
        }catch (Exception e){
            return false;
        }
    }

//    @Override
//    public Boolean deleteWishlist(int wishlistId) throws DaoException {
//        if (wishlistId == 0) {
//            throw new DaoException(4);
//        }
//        if (this.wishlistRepository.findById(wishlistId).equals(null)) {
//            throw new DaoException(4);
//        }
//
//        try {
//
//            this.wishlistRepository.deleteById(wishlistId);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) throws DaoException {
        if (wishlist.equals(null)) {
            throw new DaoException(1);
        }
        try {
            this.wishlistRepository.save(wishlist);
            return wishlist;
        } catch (Exception ex) {
            return null;
        }
    }



    @Override
    public List<Book> getBooksWishForUser(BookUser user) {


        try {
            List<Wishlist> wishlists = this.wishlistRepository.findByuserwishlist(user);
            List<Book> books = wishlists.stream().map(Wishlist::getBookwishlist)
                    .collect(Collectors.toList());
            return books;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean checkIfWish(Integer userId, Integer bookId) {

        List<Wishlist> w = this.wishlistRepository.findAll();
        for (Wishlist w1 : w) {
            if ((w1.getUserwishlist().getUserId() == userId) && (w1.getBookwishlist().getBookId() == bookId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Wishlist findByUserwishlistAndBookwishlist(BookUser bookUser, Book book) {
        return this.wishlistRepository.findByUserwishlistAndBookwishlist(bookUser,book);
    }


}
