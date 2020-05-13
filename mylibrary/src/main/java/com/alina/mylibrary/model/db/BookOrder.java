package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder {

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

   @JsonIgnoreProperties(ignoreUnknown=true, value = {"ordersbyuser"}, allowSetters = true)
   ///@JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ORDERUS_ID"))
    private BookUser ordersUser;

  //  @JsonIgnoreProperties(ignoreUnknown=true, value = {"order"}, allowSetters = true)
  @JsonIgnore
    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<OrderItem> items;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private VoucherUser vouchersForUser;

}
