package com.alina.mylibrary.controller.Interfaces.Admin;


import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.db.Voucher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/voucher")
@CrossOrigin
public interface VoucherApi {



    @GetMapping
    ApiResponse<List<Voucher>> getVouchers();

    @PostMapping
    ApiResponse<Voucher> insertVoucher(@RequestBody Voucher voucher) ;

    @PutMapping(path="/{id}")
    ApiResponse<Voucher> updateVoucher (@RequestBody Voucher voucher);

    @DeleteMapping(path="/{id}")
    ApiResponse<Boolean> deleteVoucher(@PathVariable int id);


}
