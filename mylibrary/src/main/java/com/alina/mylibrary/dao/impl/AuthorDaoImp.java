package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.AuthorDao;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.repository.AuthorRepository;
import com.alina.mylibrary.repository.BookUserRepository;
import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.List;


@Component

public class AuthorDaoImp implements AuthorDao {


    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }



    @Override
    public Author getAuthorById(int authorId) {
        return this.authorRepository.findById(authorId).orElse(null);
    }



    @Override
    public Author getAuthorByFirstName(String firstName) {
        return this.authorRepository.findByFirstName(firstName).stream().findFirst().orElse(null);
    }

    @Override
    public Author getAuthorByLastName(String lastName) {
        return this.authorRepository.findByLastName(lastName).stream().findFirst().orElse(null);
    }

    @Override
    public Author addAuthor(Author author) {
      //  try {
            this.authorRepository.save(author);
     //   }
//        catch(ConstraintViolationException ex){
////            System.out.println(ex);
////        }
////        catch(SQLGrammarException ex){
////            System.out.println(ex);
////        }
////        catch(QueryTimeoutException ex){
////            System.out.println(ex);
////        }
////        catch(GenericJDBCException ex){
////            System.out.println(ex);
////        }
////        catch(JDBCConnectionException ex){
////            System.out.println(ex);
////        }
        return author;
    }

    @Override
    public boolean deleteAuthor(int authorId) {
       if(authorId==0){
           return false;
       }
       this.authorRepository.deleteById(authorId);
       return true;
    }

    @Override
    public Author updateAuthor(Author author) {
        if(author.getAuthorId()==0){
            return null;
        }
        this.authorRepository.save(author);
        return author;
    }
}
