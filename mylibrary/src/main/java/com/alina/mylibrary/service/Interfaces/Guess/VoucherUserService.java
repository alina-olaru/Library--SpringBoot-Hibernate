package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VoucherUserService {

    VoucherUser addVoucher(VoucherUser voucherUser)  throws DBExceptions;
    VoucherUser editVoucher(VoucherUser voucherUser)  throws DBExceptions;
    List<Voucher> getVouchersForUser(BookUser user) throws DBExceptions;
    List<VoucherUser> getAllVouchers()  throws DBExceptions;
    List<VoucherUser> getVoucherUserForUser(BookUser user) ;

    /**
     * after placing a new order , the field "used" is updated if you spend the maximum uses on a voucher
     */
    VoucherUser updateVoucherAfter (VoucherUser voucherUser) throws DBExceptions;
}
