package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
