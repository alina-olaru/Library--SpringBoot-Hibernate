package com.alina.mylibrary.service.Interfaces.Guess;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MailMeService {
    List<MailWhenInStoc> GetBooksByBook(Book book);
    MailWhenInStoc addMe(MailWhenInStoc user);
    List<String> sendMails(Integer bookId);
}
