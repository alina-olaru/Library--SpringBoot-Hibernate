package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BooksAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BooksAuthorsRepository extends JpaRepository<BooksAuthors,Integer>{
    public List<BooksAuthors> findByAuthorId(Author author);
    public List<BooksAuthors> findByBookId(Book book);
}
