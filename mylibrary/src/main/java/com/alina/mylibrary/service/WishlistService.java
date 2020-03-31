package com.alina.mylibrary.service;

import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WishlistService {
    public List<Wishlist> GetAllWishlists();
    public Wishlist GetWishlistById(int wishlistId);
    public Wishlist AddWishlist(Wishlist wishlist);
    public Boolean DeleteWishlits(int wishlistId);
    public List<DashboardWishAuthorCount> GetDashboardCountForAuthors(int limit);
}
