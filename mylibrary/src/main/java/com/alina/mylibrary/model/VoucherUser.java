package com.alina.mylibrary.model;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@IdClass(VoucherUserId.class)
public class VoucherUser implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_VU_ID"))
    private BookUser usersWithVouchers;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_VOUCHER_VU_ID"))
    private Voucher vouchers;


    @Column
    private Boolean used=false;

    public VoucherUser() {
    }



    public Voucher getVouchers() {
        return vouchers;
    }

    public void setVouchers(Voucher vouchers) {
        this.vouchers = vouchers;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherUser that = (VoucherUser) o;
        return Objects.equals(usersWithVouchers, that.usersWithVouchers) &&
                Objects.equals(vouchers, that.vouchers) &&
                Objects.equals(used, that.used);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersWithVouchers, vouchers, used);
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public BookUser getUsersWithVouchers() {
        return usersWithVouchers;
    }

    public void setUsersWithVouchers(BookUser usersWithVouchers) {
        this.usersWithVouchers = usersWithVouchers;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_VOUCHERUSER_ID"))
    private BookOrder orderWithVouchers;
}
