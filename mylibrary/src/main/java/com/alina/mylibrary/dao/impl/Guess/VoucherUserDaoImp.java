package com.alina.mylibrary.dao.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Guest.VoucherUserDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import com.alina.mylibrary.repository.Guest.VoucherUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoucherUserDaoImp implements VoucherUserDao {


    @Autowired
    VoucherUserRepository voucherUserRepository;



    @Override
    public VoucherUser addVoucher(VoucherUser voucherUser) throws DaoException {
        try{
            return this.voucherUserRepository.save(voucherUser);
        }catch(Exception ex){
            throw new DaoException(1);
        }
    }

    @Override
    public VoucherUser editVoucher(VoucherUser voucherUser)  throws DaoException{
        try{
            return this.voucherUserRepository.save(voucherUser);
        }catch(Exception ex){
            throw new DaoException(2);
        }
    }


    @Override
    public List<VoucherUser> getAllVouchers() throws DaoException{
        try{
            return this.voucherUserRepository.findAll();
        }catch(Exception ex){
            throw new DaoException(3);
        }
    }
}
