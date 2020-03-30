package com.alina.mylibrary.dao;

import com.alina.mylibrary.model.Author;

import java.util.List;

public interface AuthorDao {
    public List<Author> getAuthors();
    public Author getAuthorById(int authorId);
    public Author getAuthorByFirstName(String firstName);
    public Author getAuthorByLastName(String LastName);
    public Author addAuthor(Author author);
    public boolean deleteAuthor(int authorId);
    public Author updateAuthor(Author author);


}
