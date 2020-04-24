package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.BookUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/admin/user/details")
@CrossOrigin
public interface BookUserNewsletterApi {

    @PostMapping(path="/yes/{id}")
    ApiResponse<BookUser> YesToNewsletter(@PathVariable int id);


    @PostMapping(path="/no/{id}")
    ApiResponse<BookUser> NoToNewsletter(@PathVariable int id);
}
