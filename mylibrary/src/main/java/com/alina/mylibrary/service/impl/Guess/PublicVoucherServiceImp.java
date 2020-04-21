package com.alina.mylibrary.service.impl.Guess;

import com.alina.mylibrary.dao.Interfaces.Guess.PublicVoucherDao;
import com.alina.mylibrary.service.Interfaces.Guess.PublicVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PublicVoucherServiceImp implements PublicVoucherService {

    @Autowired
    PublicVoucherDao publicVoucherDao;

    @Override
    public List<String> getLanguages() {
        return this.publicVoucherDao.GetLanguages();
    }
}
