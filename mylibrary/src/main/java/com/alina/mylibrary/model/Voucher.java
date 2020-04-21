package com.alina.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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



    @Column
    private String language;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_AUTHORR__ID"))
    private Author author_voucher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PUBLISHERR__ID"))
    private Publisher publisher_voucher;


    @OneToMany(mappedBy = "vouchers",
            cascade = CascadeType.MERGE)
    private List<VoucherUser> userVoucherLink;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_QUIZZ_ID"))
    private Quizz quizzez;
}
