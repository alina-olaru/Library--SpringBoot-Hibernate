package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.VoucherDao;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VoucherDaoImp implements VoucherDao {


    @Autowired
    private VoucherRepository voucherRepository;



    @Override
    public List<Voucher> getVouchers() {
        return this.voucherRepository.findAll();
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        return null;
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        return null;
    }

    @Override
    public Boolean deleteVoucher() {
        return null;
    }
}
