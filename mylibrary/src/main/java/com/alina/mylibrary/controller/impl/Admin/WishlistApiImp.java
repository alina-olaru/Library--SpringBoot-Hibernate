package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Guess.WishlistApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import com.alina.mylibrary.service.Interfaces.Admin.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class WishlistApiImp implements WishlistApi {

    @Autowired
    private WishlistService wishlistService;

    @Override
    public ApiResponse<Wishlist> insertWishlist(@RequestBody Wishlist wishlist) {
        Wishlist w=wishlist;
       Wishlist response=null;
       try{
           response=this.wishlistService.AddWishlist(wishlist);
           if(response!=null){
               return new  ApiResponse<Wishlist>(ApiResponseType.SUCCESS,response,"S-a inserat cu succes in baza de date");
           }

       }catch(DBExceptions e){
        return new  ApiResponse<Wishlist>(ApiResponseType.ERROR,null,e.message);

    }
    catch(Exception e){
        return new  ApiResponse<Wishlist>(ApiResponseType.ERROR,null,e.getMessage());

    }
        return new  ApiResponse<Wishlist>(ApiResponseType.ERROR,null,"A aparut o eroare interna");
    }

    @Override
    public ApiResponse<Boolean> deleteWishlist(int id) {
       Boolean response=false;
       try{
           response=this.wishlistService.DeleteWishlits(id);
           if(response!=null){
               return new  ApiResponse<Boolean>(ApiResponseType.SUCCESS,response,"S-a sters cu succes din baza de date");

           }
       }catch(DBExceptions e){
           return new  ApiResponse<Boolean>(ApiResponseType.ERROR,null,e.message);

       }
       catch(Exception e){
           return new  ApiResponse<Boolean>(ApiResponseType.ERROR,null,e.getMessage());

       }
        return new  ApiResponse<Boolean>(ApiResponseType.ERROR,null,"A aparut o eroare interna");
    }

    @Override
    public ApiResponse<List<Wishlist>> getWhislists() {
        return new ApiResponse<List<Wishlist>>(ApiResponseType.SUCCESS,this.wishlistService.GetAllWishlists());
    }

    @Override
    public   ApiResponse<List<Book>>  getWhishForUser(int id) {
        return new   ApiResponse<List<Book>>(ApiResponseType.SUCCESS,this.wishlistService.getBooksWishForUser(id));
    }

    @Override
    public ApiResponse<List<DashboardWishAuthorCount>> getDashboardCountForAuthors(int limit)
    {
        return new ApiResponse<List<DashboardWishAuthorCount>>(ApiResponseType.SUCCESS, this.wishlistService.GetDashboardCountForAuthors(limit));
    }

    @Override
    public ApiResponse<Boolean> deleteWishItem(@RequestBody BookUser user, Book book) {
        return null;
    }
}
