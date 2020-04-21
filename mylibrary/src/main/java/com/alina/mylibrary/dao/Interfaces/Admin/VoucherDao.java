package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.model.Voucher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherDao {

    List<Voucher> getVouchers();
    Voucher addVoucher(Voucher voucher);
    Voucher updateVoucher(Voucher voucher);
    Boolean deleteVoucher(int id);
}
