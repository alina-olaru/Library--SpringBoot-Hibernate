package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.AuthorDao;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.service.Interfaces.Admin.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorDao authorDao;


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
    public boolean deleteAuthor(int authorId) {
        return authorDao.deleteAuthor(authorId);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }
}
