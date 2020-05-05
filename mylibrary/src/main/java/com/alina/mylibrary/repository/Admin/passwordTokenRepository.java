package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.ConfirmationToken;
import com.alina.mylibrary.model.db.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface passwordTokenRepository extends CrudRepository<PasswordResetToken, Integer> {
}
