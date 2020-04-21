package com.alina.mylibrary.controller.Interfaces;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/Category")
@CrossOrigin


public interface CategoryApi {


    @GetMapping
    ApiResponse<List<Category>> getCategories();


    @PostMapping
    ApiResponse<com.alina.mylibrary.model.Category> insertCategory(@RequestBody com.alina.mylibrary.model.Category category);

    @PutMapping(path="/{id}")
    ApiResponse<com.alina.mylibrary.model.Category> updateCategory (@RequestBody com.alina.mylibrary.model.Category category);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteCategory(@PathVariable int id);
}
