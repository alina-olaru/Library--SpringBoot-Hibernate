package com.alina.mylibrary.dao.Interfaces.Guest;


import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailWhenInStocDao {
    List<MailWhenInStoc>GetBooksByBook(Book book);
    MailWhenInStoc addMe(MailWhenInStoc user);


}
