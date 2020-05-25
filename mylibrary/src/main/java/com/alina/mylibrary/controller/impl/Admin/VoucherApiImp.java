package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Admin.VoucherApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.service.Interfaces.Admin.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class VoucherApiImp implements VoucherApi {


    @Autowired
    private VoucherService voucherService;

    @Override
    public ApiResponse<List<Voucher>> getVouchers() {

              try{
                  return new ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS, this.voucherService.getVoucher(),"s-au adus datele cu succes");
        }catch (Exception ex){
            return new ApiResponse<List<Voucher>>(ApiResponseType.ERROR,null,ex.getMessage());

        }
    }

    @Override
    public ApiResponse<Voucher> insertVoucher(@RequestBody Voucher voucher) {
        if(voucher==null){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,null,"Voucherul nu s-a putut insera in baza de date");
        }
        Voucher response=null;
        try{
            response=this.voucherService.addVoucher(voucher);
        }catch(DBExceptions e){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,response,e.message);
        }

        return new ApiResponse<Voucher>(ApiResponseType.SUCCESS,response,"Voucherul s-a inserat cu succes");

    }

    @Override
    public ApiResponse<Voucher> updateVoucher(@RequestBody Voucher voucher) {
        if(voucher==null){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,null,"Voucherul nu a putut fi editat in baza de date");
        }
        Voucher response=null;
        try{
            response=this.voucherService.addVoucher(voucher);
        }catch(DBExceptions e){
            return new ApiResponse<Voucher>(ApiResponseType.ERROR,response,e.message);
        }

        return new ApiResponse<Voucher>(ApiResponseType.SUCCESS,response,"Voucherul a fost editat cu succes");
    }

    @Override
    public ApiResponse<Boolean> deleteVoucher(int id) {
       if(id<1){
           return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Voucherul nu a putut fi sters din baza de date");
       }


       Boolean response=false;
        try{
            response=this.voucherService.deleteVoucher(id);
        }catch(DBExceptions e){
            return new ApiResponse<Boolean>(ApiResponseType.ERROR,response,e.message);
        }


       return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response,"Voucherul s-a sters din baza de date cu succes");
    }
}
