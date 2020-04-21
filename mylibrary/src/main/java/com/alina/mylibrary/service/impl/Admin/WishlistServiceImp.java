package com.alina.mylibrary.service.impl.Admin;


import com.alina.mylibrary.dao.Interfaces.Admin.WishListDao;
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
    public Wishlist GetWishlistById(int wishlistId) {
        return this.wishListDao.getWishlistById(wishlistId);
    }

    @Override
    public Wishlist AddWishlist(Wishlist wishlist) {
        return this.wishListDao.addWishlist(wishlist);
    }

    @Override
    public Boolean DeleteWishlits(int wishlistId) {
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
