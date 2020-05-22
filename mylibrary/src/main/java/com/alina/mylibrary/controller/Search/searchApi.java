package com.alina.mylibrary.controller.Search;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/public/search")
@CrossOrigin
public interface searchApi {

    @GetMapping
    ApiResponse<HashSet<Book>> search(@RequestParam String query);
}
