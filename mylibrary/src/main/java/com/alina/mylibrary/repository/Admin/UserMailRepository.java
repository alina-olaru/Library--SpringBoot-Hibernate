package com.alina.mylibrary.repository.Admin;


import com.alina.mylibrary.model.db.BookUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMailRepository extends CrudRepository<BookUser, String>
{

    BookUser findByemailAdressIgnoreCase(String emailAdress);
}
