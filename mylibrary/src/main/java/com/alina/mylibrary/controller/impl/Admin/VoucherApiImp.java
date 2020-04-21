package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.VoucherApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.service.Interfaces.Admin.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherApiImp implements VoucherApi {


    @Autowired
    private VoucherService voucherService;

    @Override
    public ApiResponse<List<Voucher>> getVouchers() {
      return new ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS,
              this.voucherService.getVoucher(),"s-au adus datele cu succes");
    }

    @Override
    public ApiResponse<Voucher> insertVoucher(Voucher voucher) {
        if(voucher==null){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,null,"Voucherul nu s-a putut insera in baza de date");
        }

        return new ApiResponse<Voucher>(ApiResponseType.SUCCESS,this.voucherService.addVoucher(voucher),"Voucherul s-a inserat cu succes");
    }

    @Override
    public ApiResponse<Voucher> updateVoucher(Voucher voucher) {
        if(voucher==null){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,null,"Voucherul nu a putut fi editat in baza de date");
        }

        return new ApiResponse<Voucher>(ApiResponseType.SUCCESS,this.voucherService.updateVoucher(voucher),"Voucherul a fost editat cu succes");
    }

    @Override
    public ApiResponse<Boolean> deleteVoucher(int id) {
       if(id<1){
           return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Voucherul nu a putut fi sters din baza de date");
       }
       return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,this.voucherService.deleteVoucher(id),"Voucherul s-a sters din baza de date cu succes");
    }
}
