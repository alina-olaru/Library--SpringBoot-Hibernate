package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.VoucherDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.repository.Admin.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Voucher addVoucher(Voucher voucher)  throws DaoException {
        if(voucher==null){
            throw new DaoException(1);
        }
        return this.voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) throws DaoException {
        if(voucher==null){
            throw new DaoException(2);
        }
        return this.voucherRepository.save(voucher);
    }
    @Override
    public Boolean deleteVoucher(int id) throws DaoException{
        if(id==0){
            throw new DaoException(4);
        }

        this.voucherRepository.deleteById(id);
        return true;
    }
}
