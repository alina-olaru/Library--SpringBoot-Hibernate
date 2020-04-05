package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.model.Voucher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/voucher")
@CrossOrigin
public interface VoucherApi {



    @GetMapping
    ApiResponse<List<Voucher>> getVouchers();

    @PostMapping
    ApiResponse<Voucher> insertVoucher(@RequestBody Voucher voucher);

    @PutMapping(path="/{id}")
    ApiResponse<Voucher> updateVoucher (@RequestBody Voucher voucher);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteVoucher(@PathVariable int id);


}
