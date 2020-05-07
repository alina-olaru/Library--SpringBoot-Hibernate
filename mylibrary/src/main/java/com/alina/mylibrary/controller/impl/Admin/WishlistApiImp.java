package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Guess.WishlistApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Admin.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class WishlistApiImp implements WishlistApi {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private BookUserService bookUserService;

    @Autowired
    private BookService bookService;

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
    public ApiResponse<Boolean> deleteWishItem(int bookId, int userId) {
        Book book=null;
        BookUser user=null;
        try {
            book = this.bookService.getBookById(bookId);
        }catch (Exception ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,false);
        }
        try {
            user=this.bookUserService.GetUserByuserId(userId);
        }catch (Exception ex){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,false);
        }

Boolean response=false;
        Wishlist w=this.wishlistService.findByUserwishlistAndBookwishlist(user,book);
        try {
            response=this.wishlistService.DeleteWishlits(w);
          return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
        }catch (Exception e){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,false);
        }
        //return new ApiResponse<Boolean>(ApiResponseType.ERROR,false);
    }



    @Override
    public ApiResponse<Boolean> checkIfExists(int bookId, int userId) {
        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,this.wishlistService.checkIfWish(userId,bookId));
    }
}
