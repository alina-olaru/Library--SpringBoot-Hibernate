package com.alina.mylibrary.service;


import com.alina.mylibrary.model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    public Author addAuthor(Author author);
    public Author updateAuthor(Author author);
    public boolean deleteAuthor(int authorId);
}

