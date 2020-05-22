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
    @JsonIgnore
    private byte[] voucherImageDb;


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


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"author_vouchers"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_AUTHORR__ID"))
    private Author author_voucher;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"publisher_vouchers"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PUBLISHERR__ID"))
    private Publisher publisher_voucher;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"category_vouchers"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CATEGORYY__ID"))
    private Category category_voucher;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"vouchers"}, allowSetters = true)
    @OneToMany(mappedBy = "vouchers",
    fetch = FetchType.LAZY)
    private List<VoucherUser> userVoucherLink;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"vouchersGotByQuizz"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_QUIZZ_ID"))
    private Quizz quizzez;

    @Override
    public String toString() {
        return " ";
    }
}
