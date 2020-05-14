package com.alina.mylibrary.dao.Interfaces.Guest;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.model.db.VoucherUser;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface VoucherUserDao {

    VoucherUser addVoucher(VoucherUser voucherUser)  throws DaoException;
    VoucherUser editVoucher(VoucherUser voucherUser)  throws DaoException;
    List<VoucherUser> getAllVouchers()  throws DaoException;
}
