package com.alina.mylibrary.controller.Interfaces.Admin;

import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.BookUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin
public interface BookUserApi {


    @PostMapping
    ApiResponse<BookUser> insertUser(@RequestBody BookUser bookUser);


    @GetMapping
    ApiResponse<List<BookUser>>  getUser();

    @GetMapping(path="/{id}")
    ApiResponse<List<Address>> getAddressesForUser(@PathVariable int id);


    @PutMapping(path="/{id}")
    ApiResponse<BookUser> updateUser(@RequestBody BookUser bookUser);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteUser(@PathVariable int id);



}
