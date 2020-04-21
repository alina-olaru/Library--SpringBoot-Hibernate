package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.VoucherDao;
import com.alina.mylibrary.model.Voucher;
import com.alina.mylibrary.service.Interfaces.Admin.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImp implements VoucherService {

    @Autowired
    private VoucherDao voucherDao;



    @Override
    public List<Voucher> getVoucher() {
        return this.voucherDao.getVouchers();
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
       if(voucher==null){
           return null;
       }

       List<Voucher> allVouchers=this.voucherDao.getVouchers();
       for(Voucher v:allVouchers){
           if(v.equals(voucher)){
               return null;
           }
       }

       return this.voucherDao.addVoucher(voucher);
    }

    @Override
    public Boolean deleteVoucher(int voucherId) {
        if(voucherId<1){
            return null;
        }
        return this.voucherDao.deleteVoucher(voucherId);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
       if(voucher==null){
           return null;
       }
       return this.voucherDao.updateVoucher(voucher);
    }
}
