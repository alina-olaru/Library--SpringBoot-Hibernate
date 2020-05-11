package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.AuthorDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BooksAuthorsDao;
import com.alina.mylibrary.dao.Interfaces.Admin.VoucherDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.model.db.Voucher;
import com.alina.mylibrary.service.Interfaces.Admin.AuthorService;
import org.hibernate.type.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorDao authorDao;


    @Autowired
    BookDao bookDao;

    @Autowired
    BooksAuthorsDao booksAuthorsDao;

    @Autowired
    VoucherDao voucherDao;


    @Override
    public Author addAuthor(Author author) throws FieldException{
        if ((authorDao.getAuthorByFirstName(author.getFirstName()) != null) && (authorDao.getAuthorByLastName(author.getLastName()) != null)) {
            return null;
        }
        if(author.equals(null)){
            return null;
        }

        author.setFirstName(DBCheck.Stringtify(author.getFirstName()));
        author.setLastName(DBCheck.Stringtify(author.getLastName()));

        if((DBCheck.containNumber(author.getFirstName()))||(DBCheck.containNumber(author.getLastName()))){
            throw new FieldException("Numele nu poate contine cifre","firstName/lastName",Author.class.getName());
        }
        authorDao.addAuthor(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) throws FieldException {
        if ((authorDao.getAuthorByFirstName(author.getFirstName()) != null) && (authorDao.getAuthorByLastName(author.getLastName()) != null)) {
            return null;
        }
        if(author.equals(null)){
            return null;
        }


        author.setFirstName(DBCheck.Stringtify(author.getFirstName()));
        author.setLastName(DBCheck.Stringtify(author.getLastName()));

        if((DBCheck.containNumber(author.getFirstName()))||(DBCheck.containNumber(author.getLastName()))){
            throw new FieldException("Numele nu poate contine cifre","firstName/lastName",Author.class.getName());
        }
        authorDao.addAuthor(author);
        return author;
    }

    @Override
    @Transactional
    public Boolean deleteAuthor(int authorId) throws  DBExceptions {


        try {
            /**
             * Sterg autorul
             * gasesc toate cartile pe care le-a scris si le selectez doar pe cele care au un singur autor
             * sterg legaturile
             */

            List<Book> books=new ArrayList<>();
            Author auth=this.authorDao.getAuthorById(authorId);

          books=this.bookDao.getBookbyAuthor(auth.getFirstName(),auth.getLastName());
            for(Book b: books) {

                for (BooksAuthors ba : b.getBookAuthor()) {
                    //todo add sa stergi tot autoryl tau
                    if(ba.getAuthorId().equals(auth)){
                    this.booksAuthorsDao.delete(ba);
                    }
                }
            }


          List<Voucher> vouchers=auth.getAuthor_vouchers();
          for(Voucher v:vouchers) {
              this.voucherDao.deleteVoucher(v.getVoucherId());
          }

          this.authorDao.deleteAuthor(authorId);

          List<Book> booksWithAuthor=null;

            List<Book> books2=new ArrayList<>();
            List<Book> response=new ArrayList<>();

            books2=this.bookDao.getBooks();
            for(Book b:books2){
                for(BooksAuthors ba : b.getBookAuthor()){
                    if(((ba.getAuthorId().getFirstName().equals(auth.getFirstName()))&&(ba.getAuthorId().getLastName().equals(auth.getLastName())))||
                            ((ba.getAuthorId().getFirstName().equals(auth.getLastName()))&&(ba.getAuthorId().getLastName().equals(auth.getFirstName())))){

                        if(b.getBookAuthor().size()>=2) {
                            response.add(b);
                        }

                    }



                }
            }
            if(response.size()==0){
                return true;
            }
            return false;



        }
    catch (SerializationException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "author obj,probleme la serializare!!", "Insert");

        }catch(SerializationFailedException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "author obj , probleme la serializare!", "Insert");
        }
        catch (Exception ex){
                throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "author obj", "Insert");

        }

    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }
}
