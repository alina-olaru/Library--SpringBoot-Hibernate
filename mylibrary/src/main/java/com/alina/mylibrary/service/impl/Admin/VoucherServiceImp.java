package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.VoucherDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
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
    public Voucher addVoucher(Voucher voucher) throws DBExceptions {
        if (voucher == null) {
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "voucher obj", "Insert");
        }

        List<Voucher> allVouchers = this.voucherDao.getVouchers();
        for (Voucher v : allVouchers) {
            if (v.equals(voucher)) {
                throw new DBExceptions("Voucherul exista deja in baza de date", 400, this.getClass().getName(), "voucher obj", "Insert");
            }
        }

        Voucher response = null;

        try {
            response = this.voucherDao.addVoucher(voucher);
        } catch (DaoException e) {
            throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "voucher obj", "Insert");
        } catch (Exception e) {
            throw e;
        }
        return response;
    }



    @Override
    public Boolean deleteVoucher(int voucherId) throws DBExceptions{
        if(voucherId<1){
            throw new DBExceptions("Obiectul trimis este gol",404,this.getClass().getName(),"voucherId","delete");
        }
       Boolean response=false;
        try{
            response=this.voucherDao.deleteVoucher(voucherId);

        }catch(DaoException e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"voucher obj","delete");

        }catch (Exception e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"voucher obj","delete");
        }
        return response;
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) throws DBExceptions {
        if (voucher == null) {
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "voucher obj", "Insert");
        }
        Voucher response = null;
        try {


            response= this.voucherDao.updateVoucher(voucher);
        }catch(DaoException e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"voucher obj","update");

        }catch (Exception e){
            throw new DBExceptions(e.getMessage(),400,this.getClass().getName(),"voucher obj","update");
        }
        return response;
    }
}
