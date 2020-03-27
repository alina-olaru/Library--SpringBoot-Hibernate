package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional

public interface BookRepository extends JpaRepository<Book,Integer> {
}

