package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishlistService {
    public List<Wishlist> GetAllWishlists();
    public Wishlist GetWishlistById(int wishlistId) throws DBExceptions;
    public Wishlist AddWishlist(Wishlist wishlist) throws DBExceptions;
    public Boolean DeleteWishlits(int wishlistId) throws DBExceptions;
    public List<DashboardWishAuthorCount> GetDashboardCountForAuthors(int limit);
}
