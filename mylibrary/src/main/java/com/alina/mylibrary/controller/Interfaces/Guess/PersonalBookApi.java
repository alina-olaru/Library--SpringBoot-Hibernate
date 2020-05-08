package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PersonalBook;
import com.alina.mylibrary.model.db.Wishlist;
import com.alina.mylibrary.model.requests.UserAddressAdd;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/personalBook")
@CrossOrigin
public interface PersonalBookApi {


    @PostMapping
    ApiResponse<PersonalBook> addBook(@RequestBody PersonalBook personalBook);

    @GetMapping
    ApiResponse<List<PersonalBook>> getMyBooks(@RequestParam Integer type,@RequestParam Integer userId);



    @GetMapping("/my")
    ApiResponse<Boolean> checkIfPersonal(@RequestParam Integer bookId,@RequestParam Integer userId);

    @DeleteMapping("/delete")
    ApiResponse<Boolean> deletePers(@RequestParam Integer bookId,@RequestParam Integer userId);
}
