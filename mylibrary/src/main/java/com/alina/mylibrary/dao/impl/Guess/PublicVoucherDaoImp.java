package com.alina.mylibrary.dao.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guess.PublicVoucherDao;
import com.alina.mylibrary.repository.Guess.PublicVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PublicVoucherDaoImp implements PublicVoucherDao {

    @Autowired
    PublicVoucherRepository publicVoucherRepository;


    @Override
    public List<String> GetLanguages() {
        return this.publicVoucherRepository.getLanguages();

    }
}
