package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.BookUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/admin/user/details")
@CrossOrigin
public interface BookUserNewsletterApi {

    @PostMapping(path="/yes")
    ApiResponse<BookUser> YesToNewsletter(@RequestParam int id);


    @PostMapping(path="/no")
    ApiResponse<BookUser> NoToNewsletter(@RequestParam int id);
}
