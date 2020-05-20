package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BookUserDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.*;
import com.alina.mylibrary.repository.Admin.passwordTokenRepository;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Guess.BookAuthorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class BookUserServiceImpl implements BookUserService {

    @Autowired
    private BookUserDao bookUserDao;


    @Autowired
    private BookDao bookDao;


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

    @Override
    public HashSet<Book> recoomandForUser(Integer userId) throws NotFoundException, FieldException, NullPointerException, DBExceptions {
        if(userId<1){
            throw new FieldException("ID INCORECT", "BookUser", BookUser.class.getName());
        }
        BookUser user = null;
        try {
            user = this.bookUserDao.getBookUserById(userId);
        }catch(Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "BookUser obj", "get");
        }
        if(user==null){
            throw new NullPointerException();
        }

        /**
         *
         * Am in vedere titlurile din whislist si orders - >Fac split dupa spatiu si o sa constituie cuvinte cheie
         * numele si prenumele autorului
         * categoria cartilor din whishlist si orders
         * autorii in sine ( afisez cartile pt fiecare autor ,dar si autori cu nume partiale = )
         */
        List<Book> response = new ArrayList<>();

        List<String> titles = new ArrayList<>();
        List <String> names = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        /**
         * whishlist
         */
        for(Wishlist wishlist : user.getWishBooks()){
            titles.add(wishlist.getBookwishlist().getBookTitle());
          for(BooksCategories bc : wishlist.getBookwishlist().getBooksCategories()) {
              /**
               * Parcurg tabelul asociativ si pt fiecare legatura cu categoria
               * memorez categoria cartii
               *
               *
               */
              categories.add(bc.getCategories());

          }
            for(BooksAuthors ba :wishlist.getBookwishlist().getBookAuthor()){
                /**
                 * Parcurg tabelul asociativ si pt fiecare legatura autor
                 * carte ,adaug numele si prenumele autorului , titlul (care urmeaza sa fie manipulat mai jos)
                 *  si autorul(ca sa afisez toate cartile scrise de el)

                 */

                names.add(ba.getAuthorId().getFirstName());
                names.add(ba.getAuthorId().getLastName());
                authors.add(ba.getAuthorId());

            }
        }
        /**
         *
         * order
         */

        for(BookOrder order : user.getOrdersbyuser()){
            for(OrderItem item :order.getItems())
            {
                titles.add(item.getBooksorder().getBookTitle());
                for(BooksCategories bc : item.getBooksorder().getBooksCategories()){
                    categories.add(bc.getCategories());
                }

                for(BooksAuthors ba : item.getBooksorder().getBookAuthor()){
                    authors.add(ba.getAuthorId());
                    names.add(ba.getAuthorId().getLastName());
                    names.add(ba.getAuthorId().getFirstName());
                }
            }
        }


        /**
         * Aflu cuvintele cheie ->split dupa spatiu si memorez cuvintele cu lungime >=3 (fara cele de legatura)
         *
         *
         */

        List<String> keyWords = new ArrayList<>();
        for(String title : titles){
            keyWords.addAll(Arrays.asList(title.split("\\s+")));
        }



        /**
         *
         * iterez printre toate cartile si
         * dau push in response pentru toate cele care respecta
         * macar una dintre conditii ( nume de autor, id de autor , cuvinte cheie , categorie)
         */
        List<Book> books = this.bookDao.getBooks();
        for(Book book: books){
            for(String t : keyWords) {
                if (book.getBookTitle().contains(t)){
                    response.add(book);
                }
            }
            for(BooksAuthors baa : book.getBookAuthor()){
                for(Author aa : authors){
                if(baa.getAuthorId().getAuthorId()==aa.getAuthorId()){
                    response.add(book);
                }

            }
                for(String s : names){
                    if((baa.getAuthorId().getLastName().contains(s)) || (baa.getAuthorId().getFirstName().contains(s))){

                        response.add(book);
                    }
                }

            }
        }

        return new HashSet<Book>(response);
    }
}
