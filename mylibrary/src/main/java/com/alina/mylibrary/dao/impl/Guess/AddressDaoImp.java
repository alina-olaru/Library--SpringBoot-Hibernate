package com.alina.mylibrary.dao.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Guest.AddressDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.repository.Guest.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class AddressDaoImp implements AddressDao {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public Address addAddress(Address address) throws DaoException {
        try {
            return this.addressRepository.save(address);
        }catch (Exception ex) {
            throw  new DaoException(1);
        }
    }

    @Override
    public Boolean deleteAddress(Integer id) throws DaoException {
        try {
            this.addressRepository.deleteById(id);
            return true;
        }catch (Exception ex) {
            throw  new DaoException(4);
        }
        }

}
