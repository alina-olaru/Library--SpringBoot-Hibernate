package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishlistService {
    public List<Wishlist> GetAllWishlists();
    public Wishlist GetWishlistById(int wishlistId) throws DBExceptions;
    public Wishlist AddWishlist(Wishlist wishlist) throws DBExceptions;
    public Boolean DeleteWishlits(Wishlist wishlistId) throws DBExceptions;
    public List<DashboardWishAuthorCount> GetDashboardCountForAuthors(int limit);
    List<Book> getBooksWishForUser(Integer id);
    public Boolean checkIfWish(Integer userId, Integer bookId);
    public    Wishlist findByUserwishlistAndBookwishlist(BookUser bookUser, Book book);
}
