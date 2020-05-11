package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BooksAuthorsRepository extends JpaRepository<BooksAuthors,Integer>{
    public List<BooksAuthors> findByAuthorId(Author author);
    public List<BooksAuthors> findByBookId(Book book);
    List<BooksAuthors> findBooksAuthorsByAuthorId(Author author);
    List<BooksAuthors> findBooksAuthorsByBookId(Book book);
    Long deleteByAuthorId(Author author);
    Long deleteByBookId(Book book);
}
