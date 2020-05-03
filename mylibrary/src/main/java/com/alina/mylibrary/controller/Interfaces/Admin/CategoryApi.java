package com.alina.mylibrary.controller.Interfaces.Admin;


import com.alina.mylibrary.model.db.Category;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/Category")
@CrossOrigin


public interface CategoryApi {


    @GetMapping
    ApiResponse<List<Category>> getCategories();


    @PostMapping
    ApiResponse<Category> insertCategory(@RequestBody Category category);

    @PutMapping(path="/{id}")
    ApiResponse<Category> updateCategory (@RequestBody Category category);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteCategory(@PathVariable int id);
}
