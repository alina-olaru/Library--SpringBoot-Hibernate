package com.alina.mylibrary.dao.Interfaces.Guest;

import com.alina.mylibrary.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicBookDao {
    public List<Book> getBooksSF();
}
