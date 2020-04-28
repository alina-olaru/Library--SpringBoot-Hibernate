package com.alina.mylibrary.service.impl.Admin;


import com.alina.mylibrary.dao.Interfaces.Admin.WishListDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.Wishlist;
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


//        public String message;
//        public Integer code;
//        public String className;
//        public String field;
//        public String operationAffected;
        return w;
    }

    @Override
    public Wishlist AddWishlist(Wishlist wishlist) throws DBExceptions {
        if(wishlist.equals(null)){
            throw new DBExceptions("Obiectul trimis este gol",400,this.getClass().getName(),"wishlist obj","Insert");

        }
        return this.wishListDao.addWishlist(wishlist);
    }

    @Override
    public Boolean DeleteWishlits(int wishlistId)throws DBExceptions {
        if(wishlistId==0)
            return false;
        return wishListDao.deleteWishlist(wishlistId);
    }

    @Override
    public List<DashboardWishAuthorCount> GetDashboardCountForAuthors(int limit) {
        var wishlists = this.wishListDao.getAllWhislists();

        var response = wishlists.stream().collect(Collectors.groupingBy(Wishlist::getBookwishlist,
                Collectors.flatMapping(bw-> bw.getBookwishlist().getBookAuthor().stream(),
                        Collectors.groupingBy(z-> z.getAuthorId(), Collectors.counting()))));

        return null;

    }

}
