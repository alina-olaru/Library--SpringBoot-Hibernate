package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BooksAuthorsId.class)
public class BooksAuthors implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"bookAuthor"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_AUTHOR_AB_ID"))
    private Author authorId = new Author();


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"bookAuthor"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_AB_ID"))
    private Book bookId = new Book();

}

