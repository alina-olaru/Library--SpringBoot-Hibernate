package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Voucher {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucherId;


    @NotNull
    @Column(length = 30)
    private String voucherTitle;


    @Column(length = 350)
    private String voucherDescription;


    @Column
    @Lob
    private Blob voucherImage;


    @Column
    @NotNull
    private Date voucherStartDate;


    @Column
    @NotNull
    private Date voucherEndDate;


    @Column
    @NotNull
    private int voucherMaximumUses;


    @Column
    @NotNull
    private float voucherPrice;


    public Voucher() {
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }

    public Blob getVoucherImage() {
        return voucherImage;
    }

    public void setVoucherImage(Blob voucherImage) {
        this.voucherImage = voucherImage;
    }

    public Date getVoucherStartDate() {
        return voucherStartDate;
    }

    public void setVoucherStartDate(Date voucherStartDate) {
        this.voucherStartDate = voucherStartDate;
    }

    public Date getVoucherEndDate() {
        return voucherEndDate;
    }

    public void setVoucherEndDate(Date voucherEndDate) {
        this.voucherEndDate = voucherEndDate;
    }

    public int getVoucherMaximumUses() {
        return voucherMaximumUses;
    }

    public void setVoucherMaximumUses(int voucherMaximumUses) {
        this.voucherMaximumUses = voucherMaximumUses;
    }

    public float getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(float voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    //todo un quizz are un voucher,deci teoetic nu mai am nev de legatura
    //dar cum fac


    public Quizz getQuizzez() {
        return quizzez;
    }

    public void setQuizzez(Quizz quizzez) {
        this.quizzez = quizzez;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return voucherId == voucher.voucherId &&
                voucherMaximumUses == voucher.voucherMaximumUses &&
                Float.compare(voucher.voucherPrice, voucherPrice) == 0 &&
                voucherTitle.equals(voucher.voucherTitle) &&
                Objects.equals(voucherDescription, voucher.voucherDescription) &&
                Objects.equals(voucherImage, voucher.voucherImage) &&
                voucherStartDate.equals(voucher.voucherStartDate) &&
                voucherEndDate.equals(voucher.voucherEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voucherId, voucherTitle, voucherDescription, voucherImage, voucherStartDate, voucherEndDate, voucherMaximumUses, voucherPrice);
    }


    @OneToMany(mappedBy = "vouchers",
    cascade = CascadeType.ALL)
    private Set<VoucherUser> userVoucherLink;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_QUIZZ_ID"))
    private Quizz quizzez;
}
