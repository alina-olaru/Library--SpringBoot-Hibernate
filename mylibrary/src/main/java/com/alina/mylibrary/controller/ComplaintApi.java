package com.alina.mylibrary.controller;

import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.model.Complaint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ComplaintApi {

    @GetMapping
    ApiResponse<List<Complaint>> getComplaints();


    @PostMapping
    ApiResponse<Complaint> insertComplaint(@RequestBody Complaint complaint);

}





