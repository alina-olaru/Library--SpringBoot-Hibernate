package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public interface OrderApi {

    @PostMapping()
    ApiResponse<BookOrder> addOrder(@RequestBody BookOrder order);

    @GetMapping
    ApiResponse<List<BookOrder>> getOrdersByUser(@RequestParam Integer userId);

    @GetMapping("/all")
    ApiResponse<List<BookOrder>> getOrders();
}
