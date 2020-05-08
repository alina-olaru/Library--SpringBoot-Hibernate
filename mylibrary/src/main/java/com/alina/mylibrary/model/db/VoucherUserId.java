package com.alina.mylibrary.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherUserId implements Serializable {
    private int usersWithVouchers;
    private int vouchers;

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


}
