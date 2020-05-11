package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.AuthorDao;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.repository.Admin.AuthorRepository;
import com.alina.mylibrary.repository.Custom.AuthorCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component

public class AuthorDaoImp implements AuthorDao {


    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorCustomRepository authorCustomRepository;


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

        this.authorRepository.save(author);
        return author;
    }

    @Override
    public boolean deleteAuthor(int authorId) {


        Author auth = this.getAuthorById(authorId);
        this.authorRepository.delete(auth);
        try {
             this.authorRepository.delete(auth);
             return true;

        } catch (Exception ex){}
        return false;
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

