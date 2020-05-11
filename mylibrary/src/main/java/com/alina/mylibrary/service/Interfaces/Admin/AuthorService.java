package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.db.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author addAuthor(Author author) throws FieldException;
    public Author updateAuthor(Author author) throws FieldException;
    public Boolean deleteAuthor(int authorId) throws DBExceptions;
    public List<Author> getAuthors();
}

