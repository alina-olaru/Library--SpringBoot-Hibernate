package com.alina.mylibrary.service;


import com.alina.mylibrary.model.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {

    public List<Voucher> getVoucher();
    public Voucher addVoucher(Voucher voucher);
    public Boolean deleteVoucher(int voucherId);
    public Voucher updateVoucher(Voucher voucher );



}
