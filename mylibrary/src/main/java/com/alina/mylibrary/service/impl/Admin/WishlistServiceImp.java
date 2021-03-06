package com.alina.mylibrary.service.impl.Admin;


import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.dao.Interfaces.Admin.WishListDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import com.alina.mylibrary.service.Interfaces.Admin.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImp implements WishlistService {

    @Autowired
    private WishListDao wishListDao;

    @Autowired
    private BookUserDao bookUserDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Wishlist> GetAllWishlists() {

        return this.wishListDao.getAllWhislists();

    }

    @Override
    public Wishlist GetWishlistById(int wishlistId)throws DBExceptions
    {

        Wishlist w= null;
        try{
           w= this.wishListDao.getWishlistById(wishlistId);
            if(w.equals(null)){
                throw new DBExceptions("Nu s-a gasit obiectul cu id-ul dat",404,this.getClass().getName(),"wishlistId","Insert");
            }
        }catch(DBExceptions e){
            throw e;
        }


        return w;
    }

    @Override
    public Wishlist AddWishlist(Wishlist wishlist) throws DBExceptions {
        if(wishlist.getBookwishlist().equals(null)){
            throw new DBExceptions("Obiectul carte trimis este gol",400,this.getClass().getName(),"wishlist obj","Insert");

        }
        if(wishlist.getUserwishlist().equals(null)){
            throw new DBExceptions("Obiectul user trimis este gol",400,this.getClass().getName(),"wishlist obj","Insert");

        }
        Wishlist response=null;
        try {
            response = wishListDao.addWishlist(wishlist);
        }catch(DaoException e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"wishlist obj","insert");

        }catch (Exception e){
            throw new DBExceptions(e.getMessage(),500,this.getClass().getName(),"wishlist obj","insert");

        }
        return response;
    }

    @Override
    public Boolean DeleteWishlits(BookUser bookUser, Book book) throws DBExceptions {
        try {
            return this.wishListDao.deleteWishlist(bookUser,book);
        }catch (Exception e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"wishlist obj","delete");

        }
    }



    @Override
    public List<DashboardWishAuthorCount> GetDashboardCountForAuthors(int limit) {
        var wishlists = this.wishListDao.getAllWhislists();

        var response = wishlists.stream().collect(Collectors.groupingBy(Wishlist::getBookwishlist,
                Collectors.flatMapping(bw-> bw.getBookwishlist().getBookAuthor().stream(),
                        Collectors.groupingBy(z-> z.getAuthorId(), Collectors.counting()))));

        return null;

    }

    @Override
    public List<Book> getBooksWishForUser(Integer id) {

        BookUser user = this.bookUserDao.getBookUserById(id);

        return this.wishListDao.getBooksWishForUser(user);

    }

    @Override
    public Boolean checkIfWish(Integer userId, Integer bookId) {
        return this.wishListDao.checkIfWish(userId,bookId);
    }

    @Override
    public Wishlist findByUserwishlistAndBookwishlist(BookUser bookUser, Book book) {
       return this.wishListDao.findByUserwishlistAndBookwishlist(bookUser,book);
    }

}
