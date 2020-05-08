package com.alina.mylibrary.model.dashboard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryNumberBooks {



    @Column(name="titleOfCategory")
    private String titleOfCategory;

    @Column(name="numberBooksforCategory")
    private Integer numberBooksforCategory;


}

//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Subselect("select c.categoryTitle as numberBooksforCategory ,count(c.categoryId) as titleOfCategory \n" +
//        "from Category  c \n" +
//        "join BooksCategories bc\n" +
//        "on c.categoryId=bc.categories.categoryId \n" +
//        "group by c.categoryTitle")
//@Synchronize({"Book","BooksCategories"})
//public class CategoryNumberBooks {
//
//
//    private Integer numberBooksforCategory;
//
//    @Id
//    private String titleOfCategory;
//
//
//}