package com.alina.mylibrary.repository;


import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetTokenRepository  extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findBytoken(String token);
}






