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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
