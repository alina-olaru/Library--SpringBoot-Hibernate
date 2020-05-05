package com.alina.mylibrary.service.impl.Guess;


import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PasswordResetToken;
import com.alina.mylibrary.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    public BookUser getUserByToken(String token){
        PasswordResetToken response = this.passwordResetTokenRepository.findBytoken(token);
        BookUser user=response.getUser();
        return user;
    }
}
