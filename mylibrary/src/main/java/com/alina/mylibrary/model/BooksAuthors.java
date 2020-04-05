package com.alina.mylibrary.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BooksAuthorsId.class)
public class BooksAuthors implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(foreignKey = @ForeignKey(name="FK_AUTHOR_AB_ID"))
    private Author authorId = new Author();


    @Id
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_AB_ID"))
    private Book bookId = new Book();

}

