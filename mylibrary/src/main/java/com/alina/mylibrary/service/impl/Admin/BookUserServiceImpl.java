package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.PasswordResetToken;
import com.alina.mylibrary.repository.Admin.passwordTokenRepository;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookUserServiceImpl implements BookUserService {

    @Autowired
    private BookUserDao bookUserDao;

    @Autowired
    private passwordTokenRepository passwordTokenRepository;

    @Override
    public BookUser GetUserByUsernameOrEmail(String searchKey) {
        var user = this.bookUserDao.getBookUserByUsername(searchKey);
        if (user == null) {
            user = this.bookUserDao.getBookUserByEmail(searchKey);
        }
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public List<BookUser> getUsers() {
        return this.bookUserDao.getBookUsers();
    }

    @Override
    public BookUser addUser(BookUser bookUser) {
        if (bookUser == null) {
            return null;
        }
        return this.bookUserDao.addBookUser(bookUser);
    }

    @Override
    public BookUser editUser(BookUser bookUser) {
        if (bookUser == null) {
            return null;
        }
        return this.bookUserDao.updateBookUser(bookUser);
    }

    @Override
    public Boolean deleteUser(int id) {
        return this.bookUserDao.removeBookUser(id);
    }

    @Override
    public BookUser GetUserByuserId(Integer userId) {
        return this.bookUserDao.getBookUserById(userId);
    }

    @Override
    public BookUser yesNewsletter(Integer userId) throws NotFoundException, FieldException {
        BookUser user = this.bookUserDao.getBookUserById(userId);
        if (user.equals(null)) {
            throw new NotFoundException("Nu exista user cu acest id");
        }
        if (user.isNewsletter() == true) {
            throw new FieldException("Campul este deja true,operatia nu are sens", "newsletter", user.getClass().getName());
        }

        user.setNewsletter(true);
        return this.bookUserDao.updateBookUser(user);
    }

    @Override
    public BookUser NoNewsletter(Integer userId) throws NotFoundException, FieldException  {
        BookUser user = this.bookUserDao.getBookUserById(userId);
        if (user.equals(null)) {
            throw new NotFoundException("Nu exista user cu acest id");
        }
        if (user.isNewsletter() == false) {
            throw new FieldException("Campul este deja false,operatia nu are sens", "newsletter", user.getClass().getName());
        }

        user.setNewsletter(false);
        return this.bookUserDao.updateBookUser(user);
    }

    @Override
    public BookUser addAddress(Address address , BookUser bookUser) throws NotFoundException, FieldException,NullPointerException {
       if(address.equals(null)){
           throw new NullPointerException("Adresa este goala");
       }


        address.setBlock(DBCheck.Stringtify(address.getBlock()));
        address.setCity(DBCheck.Stringtify(address.getCity()));
        address.setProvince(DBCheck.Stringtify(address.getProvince()));
        address.setStreetName(DBCheck.Stringtify(address.getStreetName()));
        if(DBCheck.containNumber(address.getStreetName())){
            throw new FieldException("Numele nu poate contine cifre","StreetName",address.getClass().getName());
        }

        address.setUserAddress(bookUser);

        bookUser.getAddresses().add(address);
      return  this.bookUserDao.updateBookUser(bookUser);
       }

    @Override
    public void createPasswordResetTokenForUser(BookUser user, String token) {
        PasswordResetToken myToken=new PasswordResetToken(token,user);

        this.passwordTokenRepository.save(myToken);

    }

    @Override
    public List<Address> getAddress(int id) {
        BookUser user=this.bookUserDao.getBookUserById(id);
        return user.getAddresses();
//        List<Address> addresses=new ArrayList<>();
//        for(Address ad:user.getAddresses()){
//            addresses.add(ad);
//        }
//
//        return addresses;
    }
}
