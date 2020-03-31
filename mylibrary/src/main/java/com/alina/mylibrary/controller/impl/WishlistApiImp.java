package com.alina.mylibrary.controller.impl;


import com.alina.mylibrary.controller.WishlistApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import com.alina.mylibrary.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class WishlistApiImp implements WishlistApi {

    @Autowired
    private WishlistService wishlistService;

    @Override
    public ApiResponse<Wishlist> inserWishlist(Wishlist wishlist) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> deleteWishlist(int id) {
        return null;
    }

    @Override
    public ApiResponse<List<Wishlist>> getWhislists() {
        return null;
    }

    @Override
    public ApiResponse<List<DashboardWishAuthorCount>> getDashboardCountForAuthors(int limit) {
        return new ApiResponse<List<DashboardWishAuthorCount>>(ApiResponseType.SUCCESS, this.wishlistService.GetDashboardCountForAuthors(limit));
    }
}