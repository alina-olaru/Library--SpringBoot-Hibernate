package com.alina.mylibrary.repository.Guess;

import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.PersonalBook;
import com.alina.mylibrary.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class PublicVoucherRepository  {



    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<String> getLanguages()  {



        return (List<String>) namedParameterJdbcTemplate.query(
                "select distinct BOOK_LANGUAGE from book",
                new BeanPropertyRowMapper<String>(String.class));


    }
}
