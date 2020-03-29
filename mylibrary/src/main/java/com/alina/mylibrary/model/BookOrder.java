package com.alina.mylibrary.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class BookOrder{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @NotNull
    @Column
    private Date orderD;


    @NotNull
    @Column
    private float shipping;


    @NotNull
    @Column
    private float voucherDiscount;


    @NotNull
    @Column
    private float subtotal;


    @NotNull
    @Column
    private float total;

    @NotNull
    @Column
    private int numberItems;



    public BookOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderD() {
        return orderD;
    }

    public void setOrderD(Date orderD) {
        this.orderD = orderD;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public float getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(float voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ORDERUS_ID"))
    private BookUser ordersUser;


    @OneToMany(mappedBy = "order",
    cascade = CascadeType.ALL)
    private List<OrderItem> items;



    @OneToOne(mappedBy = "orderWithVouchers",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    optional = false)
    private VoucherUser vouchersForUser;


    public void setVouchersForUser(VoucherUser detailsAboutVoucher){
        if(detailsAboutVoucher==null){
            if(this.vouchersForUser==null){
                this.vouchersForUser.setVouchers(null);
            }
        }
        else{
            vouchersForUser.setVouchers(null);
        }
        this.vouchersForUser=detailsAboutVoucher;
    }
}
