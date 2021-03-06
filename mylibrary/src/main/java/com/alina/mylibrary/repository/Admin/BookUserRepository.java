package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//public interface BookUserRepository extends CrudRepository<BookUser,Integer> {
//}

@Repository
@Transactional
public interface BookUserRepository extends JpaRepository<BookUser, Integer> {
    List<BookUser> findByEmailAdress(String emailAdress);
    List<BookUser> findByUsername(String username);
}

