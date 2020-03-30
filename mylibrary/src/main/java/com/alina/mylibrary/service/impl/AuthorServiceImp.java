package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.dao.AuthorDao;
import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorDao authorDao;


    @Override
    public Author addAuthor(Author author) {
        if ((authorDao.getAuthorByFirstName(author.getFirstName()) == null) && (authorDao.getAuthorByLastName(author.getLastName()) == null)) {
            return null;
        }
       authorDao.addAuthor(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        authorDao.updateAuthor(author);
        return author;
    }

    @Override
    public boolean deleteAuthor(int authorId) {
        return authorDao.deleteAuthor(authorId);
    }
}
