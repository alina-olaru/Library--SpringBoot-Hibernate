package com.alina.mylibrary.dao.Interfaces.Guess;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicVoucherDao {

    public List<String> GetLanguages();
}
