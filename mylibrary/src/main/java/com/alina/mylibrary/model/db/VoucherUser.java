package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(VoucherUserId.class)
public class VoucherUser implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userVoucherLink"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_VU_ID"))
    private BookUser usersWithVouchers;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userVoucherLink"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_VOUCHER_VU_ID"))
    private Voucher vouchers;


    @Column
    private Boolean used=false;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"vouchersForUser"}, allowSetters = true)
    @OneToMany(mappedBy = "vouchersForUser",
            fetch = FetchType.LAZY)
    private List<BookOrder> orderWithVouchers;

    @Override
    public String toString() {
        return " ";
    }
}
