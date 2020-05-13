package com.alina.mylibrary.service.impl.Guess;


import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.dao.Interfaces.Guest.AddressDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.service.Interfaces.Guess.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {


    @Autowired
    AddressDao addressDao;

    @Autowired
    BookUserDao bookUserDao;

    @Override
    public BookUser addAddress(Address address, Integer userId) throws DBExceptions {
        if(address==null){
            throw new DBExceptions("Obiectul trimis este gol", 404, this.getClass().getName(), "address obj", "Insert");
        }

        try{
            BookUser user=this.bookUserDao.getBookUserById(userId);
            address.setUserAddress(user);
             this.addressDao.addAddress(address);
            return user;
        }catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "address obj", "Insert");


        }
    }

    @Override
    public Boolean deleteAddress(Integer id) throws DBExceptions {
      try{
          return this.addressDao.deleteAddress(id);
      }catch (Exception ex){
          throw new DBExceptions(ex.getMessage(), 400, this.getClass().getName(), "address obj", "Insert");

      }
    }
}
