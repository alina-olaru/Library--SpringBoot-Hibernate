package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Publisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edituri")
@CrossOrigin
public interface PublisherApi {

    @PostMapping
    ApiResponse<Publisher> inserPublisher(@RequestBody Publisher publisher);

    @PutMapping(path="/{id}")
    ApiResponse<Publisher> updatePublisher(@RequestBody Publisher publisher);

    @DeleteMapping(path = "/{id}")
    ApiResponse<Boolean> deletePublisher(@PathVariable int id);

    @GetMapping
    ApiResponse<List<Publisher>> getPublishers();
}


