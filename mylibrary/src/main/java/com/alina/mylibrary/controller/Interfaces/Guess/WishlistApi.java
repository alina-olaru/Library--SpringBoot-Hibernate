package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//todo jwt interceptor si de schimbat api urile
@RestController
@RequestMapping("/api/user/Wishlist")
@CrossOrigin
public interface WishlistApi {

    @PostMapping
    ApiResponse<Wishlist> insertWishlist(@RequestBody Wishlist wishlist);


    @GetMapping
    ApiResponse<List<Wishlist>> getWhislists();

    @GetMapping(path="/{id}")
    ApiResponse<List<Book>> getWhishForUser(@PathVariable int id);

    @GetMapping(path = "/dashboard/author")
    ApiResponse<List<DashboardWishAuthorCount>> getDashboardCountForAuthors(@RequestParam int limit);


    @DeleteMapping
    ApiResponse<Boolean> deleteWishItem(@RequestParam int bookId, @RequestParam int userId);

    @GetMapping("/exists")
    ApiResponse<Boolean> checkIfExists(@RequestParam int bookId, @RequestParam int userId);

}
