package com.alina.mylibrary.dao.Interfaces.Guest;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicVoucherDao {

    public List<String> GetLanguages();
}
