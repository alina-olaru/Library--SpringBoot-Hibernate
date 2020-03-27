package com.alina.mylibrary.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class VoucherUserId implements Serializable {
    private int usersWithVouchers;
    private int vouchers;

    public int getUsersWithVouchers() {
        return usersWithVouchers;
    }

    public void setUsersWithVouchers(int usersWithVouchers) {
        this.usersWithVouchers = usersWithVouchers;
    }

    public int getVouchers() {
        return vouchers;
    }

    public void setVouchers(int vouchers) {
        this.vouchers = vouchers;
    }
}
