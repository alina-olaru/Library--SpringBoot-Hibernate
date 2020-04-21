package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.model.Wishlist;
import com.alina.mylibrary.model.view.dashboard.DashboardWishAuthorCount;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//todo jwt interceptor si de schimbat api urile
@RestController
@RequestMapping("/Wishlist")
@CrossOrigin
public interface WishlistApi {

    @PostMapping
    ApiResponse<Wishlist> inserWishlist(@RequestBody Wishlist wishlist);


    @DeleteMapping(path = "/{id}")
    ApiResponse<Boolean> deleteWishlist(@PathVariable int id);

    @GetMapping
    ApiResponse<List<Wishlist>> getWhislists();

    @GetMapping(path = "/dashboard/author")
    ApiResponse<List<DashboardWishAuthorCount>> getDashboardCountForAuthors(@RequestParam int limit);

}
