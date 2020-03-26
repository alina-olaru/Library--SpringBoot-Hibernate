package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
