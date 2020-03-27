package com.alina.mylibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table
@IdClass(BooksAuthorsId.class)
public class BooksAuthors implements Serializable {



    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_AUTHOR_AB_ID"))
    private Author authorId;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_AB_ID"))
    private Book bookId;

    public BooksAuthors() {
    }


    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}

