package com.alina.mylibrary.repository.Guest;

import com.alina.mylibrary.model.VoucherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface VoucherUserRepository extends JpaRepository<VoucherUser, Integer>{
}


