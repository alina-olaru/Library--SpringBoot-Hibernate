package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Complaint;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/public/api/cereri")
@CrossOrigin
public interface ComplaintApi {

    @GetMapping
    ApiResponse<List<Complaint>> getComplaints();


    @PostMapping
    ApiResponse<Complaint> insertComplaint(@RequestBody Complaint complaint);


    @GetMapping(path = "/{id}")
    ApiResponse<List<Complaint>> getComplaintById(@PathVariable Integer id);

}





