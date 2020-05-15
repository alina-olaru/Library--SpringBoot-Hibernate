package com.alina.mylibrary.controller.Interfaces.Guess;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/vouchers/user")
@CrossOrigin
public interface VoucherUserApi {

    @PostMapping
    ApiResponse<VoucherUser> addVoucher(@RequestBody VoucherUser voucherUser);


    @PostMapping("/user")
    ApiResponse<List<Voucher>> getBookById(@RequestBody BookUser user);


    @PostMapping("/user/all")
    ApiResponse<List<Voucher>> getVoucherUserById(@RequestBody BookUser user);


    @PostMapping("/user/after")
    ApiResponse<VoucherUser> updateVoucherAfter(@RequestBody VoucherUser voucherUser);
}

