package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.VoucherDao;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class VoucherDaoImp implements VoucherDao {


    @Autowired
    private VoucherRepository voucherRepository;



    @Override
    public List<Voucher> getVouchers() {

        return this.voucherRepository.findAll();

    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        if(voucher==null){
            return null;
        }
        return this.voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        if(voucher==null){
            return null;
        }
        return this.voucherRepository.save(voucher);
    }
    @Override
    public Boolean deleteVoucher(int id) {
        if(id==0){
            return false;
        }
        this.voucherRepository.deleteById(id);
        return true;
    }
}
