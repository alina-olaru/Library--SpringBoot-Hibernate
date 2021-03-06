package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public interface BookUserService {


    public BookUser GetUserByUsernameOrEmail(String searchKey);
    public  List<BookUser> getUsers();
    public BookUser addUser(BookUser bookUser);
    public BookUser editUser(BookUser bookUser);
    public Boolean deleteUser(int bookUserId);
    public BookUser GetUserByuserId(Integer userId);
    public BookUser yesNewsletter(Integer userId)  throws NotFoundException, FieldException;
    public BookUser NoNewsletter(Integer userId)  throws NotFoundException, FieldException ;
    public BookUser addAddress(Address address,BookUser bookUser)  throws NotFoundException, FieldException,NullPointerException;

    public void createPasswordResetTokenForUser(BookUser user, String token);
    public List<Address> getAddress(int id);
    public HashSet<Book> recoomandForUser(Integer userId)   throws NotFoundException, FieldException,NullPointerException, DBExceptions;

}
