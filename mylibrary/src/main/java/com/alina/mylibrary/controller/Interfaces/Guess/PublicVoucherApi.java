package com.alina.mylibrary.controller.Interfaces.Guess;


import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/api/vouchers")
@CrossOrigin
public interface PublicVoucherApi {


    @GetMapping("/languages")
    ApiResponse<List<String>> getLanguages();
}
