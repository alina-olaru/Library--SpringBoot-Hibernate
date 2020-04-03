package com.alina.mylibrary.repository;


import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BookUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMailRepository extends CrudRepository<BookUser, String>
{

    BookUser findByemailAdressIgnoreCase(String emailAdress);
}
