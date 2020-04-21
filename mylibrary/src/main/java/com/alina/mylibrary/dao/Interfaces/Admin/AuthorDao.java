package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorDao {
    public List<Author> getAuthors();
    public Author getAuthorById(int authorId);
    public Author getAuthorByFirstName(String firstName);
    public Author getAuthorByLastName(String LastName);
    public Author addAuthor(Author author);
    public boolean deleteAuthor(int authorId);
    public Author updateAuthor(Author author);


}
