package com.alina.mylibrary.service.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Guest.VoucherUserDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import com.alina.mylibrary.service.Interfaces.Guess.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherUserServiceImp implements VoucherUserService {



    @Autowired
    VoucherUserDao voucherUserDao;

    @Override
    public VoucherUser addVoucher(VoucherUser voucherUser) throws DBExceptions {
        if((voucherUser.getUsersWithVouchers()==null)||(voucherUser.getVouchers()==null)){
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "voucherusert obj", "Insert");
        }

        if(voucherUser.getUsersWithVouchers().getIsEnabled()==false){
            throw new DBExceptions("Contul nu este verificat.Va rugam sa va verificati mailul.", 400, this.getClass().getName(), "voucher obj", "Insert");
        }

        if(voucherUser.getUsersWithVouchers().getBlocked()==true){
            throw new DBExceptions("CONT BLOCAT! Contul nu poate adauga vouchere.Contactati-ne pentru detalii.", 400, this.getClass().getName(), "voucher obj", "Insert");

        }

        try {
            return this.voucherUserDao.addVoucher(voucherUser);
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 500, this.getClass().getName(), "voucher obj", "Insert");

        }
    }

    @Override
    public VoucherUser editVoucher(VoucherUser voucherUser) throws DBExceptions {
        if((voucherUser.getUsersWithVouchers()==null)||(voucherUser.getOrderWithVouchers().size()==0)){
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "voucherusert obj", "update");
        }

        if(voucherUser.getUsersWithVouchers().getIsEnabled()==false){
            throw new DBExceptions("Contul nu este verificat.Va rugam sa va verificati mailul.", 400, this.getClass().getName(), "voucher obj", "update");
        }

        if(voucherUser.getUsersWithVouchers().getBlocked()==true){
            throw new DBExceptions("CONT BLOCAT! Contul nu poate adauga vouchere.Contactati-ne pentru detalii.", 400, this.getClass().getName(), "voucher obj", "update");

        }

        try {
            return this.voucherUserDao.editVoucher(voucherUser);
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 500, this.getClass().getName(), "voucher obj", "Insert");

        }
    }

    @Override
    public List<Voucher> getVouchersForUser(BookUser user) throws DBExceptions {
        List<VoucherUser> vouchers = null;
        try{
            vouchers =  this.voucherUserDao.getAllVouchers();

        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 500, this.getClass().getName(), "voucher obj", "get");

        }
        if(vouchers.size()==0){
            return null;
        }

        List<Voucher> response=new ArrayList<>();

        for(VoucherUser v:vouchers){
            if(v.getUsersWithVouchers().getUserId()==user.getUserId()){
                response.add(v.getVouchers());
            }
        }


        return response;
    }

    @Override
    public List<VoucherUser> getAllVouchers() throws DBExceptions {
        try{
            return this.voucherUserDao.getAllVouchers();

        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 500, this.getClass().getName(), "voucher obj", "get");

        }
    }

    @Override
    public List<VoucherUser> getVoucherUserForUser(BookUser user) {
        List<VoucherUser> response = new ArrayList<>();
        List<VoucherUser> all=new ArrayList<>();
        try {
             all = this.voucherUserDao.getAllVouchers();
        }catch(Exception ex){
            return null;
        }
        for(VoucherUser v:all){
            if(v.getUsersWithVouchers().getUserId()==user.getUserId()){
                response.add(v);
            }
        }
        return  response;
    }
}
