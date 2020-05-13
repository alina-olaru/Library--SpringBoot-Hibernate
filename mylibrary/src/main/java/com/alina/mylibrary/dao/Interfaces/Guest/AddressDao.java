package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Address;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressDao {

    Address addAddress(Address address) throws DaoException;
    Boolean deleteAddress(Integer id) throws DaoException;

}
