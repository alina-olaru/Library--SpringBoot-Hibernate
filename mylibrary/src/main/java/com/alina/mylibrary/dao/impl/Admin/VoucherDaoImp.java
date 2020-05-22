package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.VoucherDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.repository.Admin.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.List;


@Repository
public class VoucherDaoImp implements VoucherDao {


    @Autowired
    private VoucherRepository voucherRepository;


    @Override
    public List<Voucher> getVouchers() {
        LobHandler blobHandler = new DefaultLobHandler();
        var x = this.voucherRepository.findAll();
        return x;
    }

    @Override
    public Voucher addVoucher(Voucher voucher) throws DaoException {
        try {

        if (voucher == null) {
            throw new DaoException(1);
        }

        return this.voucherRepository.save(voucher);
        } catch (Exception ex){

            var x = new Voucher();
            x.setVoucherDescription(ex.getMessage());
            return x;
        }
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) throws DaoException {
        if (voucher == null) {
            throw new DaoException(2);
        }

        List<Voucher> allVouchers = this.voucherRepository.findAll();
        for (Voucher v : allVouchers) {
            if (v.equals(voucher)) {
                throw new DaoException(2);
            }
        }

        return this.voucherRepository.save(voucher);
    }

    @Override
    public Boolean deleteVoucher(int id) throws DaoException {
        if (id == 0) {
            throw new DaoException(4);
        }
        if (this.voucherRepository.findById(id).equals(null)) {
            throw new DaoException(4);
        }

        this.voucherRepository.deleteById(id);
        return true;
    }
}
