package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {

    public List<Voucher> getVoucher() throws DBExceptions ;
    public Voucher addVoucher(Voucher voucher)  throws DBExceptions;
    public Boolean deleteVoucher(int voucherId)  throws DBExceptions;
    public Voucher updateVoucher(Voucher voucher )  throws DBExceptions;



}
