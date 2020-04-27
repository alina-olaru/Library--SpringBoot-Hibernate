package com.alina.mylibrary.repository.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
