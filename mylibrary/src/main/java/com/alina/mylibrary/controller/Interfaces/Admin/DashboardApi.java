package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/public/api/admin/dashboard")
@CrossOrigin
public interface DashboardApi {

    @GetMapping("/categories/count")
    public ApiResponse<List<DashboardClass>> getCategoriesWithNumberBooks();

    @GetMapping("/authors/count")
    public  ApiResponse<List<DashboardClass>> getAuthorsNumberBooks();

    @GetMapping("/authors/books/cat")
    public  ApiResponse<List<DashboardThreeItemsClass>> getBooksWithAutCat();

}
