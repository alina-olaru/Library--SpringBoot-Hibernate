package com.alina.mylibrary.controller.impl;


import com.alina.mylibrary.controller.VoucherApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.service.VoucherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class VoucherApiImp implements VoucherApi {


    VoucherService voucherService;

    @Override
    public ApiResponse<List<Voucher>> getVouchers() {
      return new ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS,
              this.voucherService.getVoucher(),"s-au adus datele cu succes");
    }

    @Override
    public ApiResponse<Voucher> insertVoucher(Voucher voucher) {
        return null;
    }

    @Override
    public ApiResponse<Voucher> updateVoucher(Voucher voucher) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> deleteVoucher(int id) {
        return null;
    }
}
