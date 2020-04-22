package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.FieldException;
import com.alina.mylibrary.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author addAuthor(Author author) throws FieldException;
    public Author updateAuthor(Author author) throws FieldException;
    public boolean deleteAuthor(int authorId);
    public List<Author> getAuthors();
}

