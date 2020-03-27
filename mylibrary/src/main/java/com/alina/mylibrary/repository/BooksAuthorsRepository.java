package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BooksAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BooksAuthorsRepository extends JpaRepository<BooksAuthors,Integer>{
}
