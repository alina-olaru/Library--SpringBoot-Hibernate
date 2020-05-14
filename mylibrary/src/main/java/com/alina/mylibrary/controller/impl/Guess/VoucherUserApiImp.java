package com.alina.mylibrary.controller.impl.Guess;


import com.alina.mylibrary.controller.Interfaces.Guess.VoucherUserApi;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherUserApiImp implements VoucherUserApi {


    @Autowired
    VoucherUserService voucherUserService;


    @Override
    public ApiResponse<VoucherUser> addVoucher(VoucherUser voucherUser) {

        try{
           VoucherUser response= this.voucherUserService.addVoucher(voucherUser);
           return new ApiResponse<VoucherUser>(ApiResponseType.SUCCESS,response,"s-a inserat cu succes");

        }catch (DBExceptions ex){
            return new ApiResponse<VoucherUser>(ApiResponseType.ERROR,null,ex.getMessage());


        }catch (Exception ex){
            return new ApiResponse<VoucherUser>(ApiResponseType.ERROR,null,ex.getMessage() + " " + " a aparut o eroare ");

        }

    }

    @Override
    public ApiResponse<List<Voucher>> getBookById(BookUser user) {
        try{
            return new  ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS,this.voucherUserService.getVouchersForUser(user),"s-au adus voucherele cu succes");
        }catch(Exception ex){
            return new  ApiResponse<List<Voucher>>(ApiResponseType.ERROR,null,"s-au adus voucherele cu succes"+ex.getMessage());

        }
    }

    @Override
    public ApiResponse<List<Voucher>> getVoucherUserById(BookUser user) {
        try{
            return new ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS,this.voucherUserService.getVouchersForUser(user),"e ok");
        }catch(Exception ex){
            return new ApiResponse<List<Voucher>>(ApiResponseType.SUCCESS,null,ex.getMessage());

        }
    }


}
