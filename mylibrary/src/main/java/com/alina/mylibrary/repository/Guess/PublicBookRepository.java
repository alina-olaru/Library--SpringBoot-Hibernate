package com.alina.mylibrary.repository.Guess;


import com.alina.mylibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublicBookRepository {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Book> getBookSF() {


        return (List<Book>) namedParameterJdbcTemplate.query(
"select book_id,BOOK_IMAGE,BOOK_PRICE,BOOK_RATING,BOOK_TITLE,CATEGORY_TITLE\n" +
        "from book b\n" +
        "join BOOKS_CATEGORIES bc\n" +
        "on b.book_id=bc.BOOKS_C___BOOK_ID\n" +
        "join CATEGORY c\n" +
        "on bc.CATEGORIES___CATEGORY_ID=c.CATEGORY_ID\n" +
        "where lower(category_title)='sf'",
                new BeanPropertyRowMapper<Book>(Book.class)
        );


    }
}
