package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin
public interface DashboardApi {

    @GetMapping("/categories/count")
    public ApiResponse<List<CategoryNumberBooks>> getCategoriesWithNumberBooks();
}
