package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Voucher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherDao {

    List<Voucher> getVouchers();
    Voucher addVoucher(Voucher voucher)  throws DaoException;
    Voucher updateVoucher(Voucher voucher)  throws DaoException;
    Boolean deleteVoucher(int id)  throws DaoException;
}
