package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface AuthorRepository  extends JpaRepository<Author,Integer> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
}
