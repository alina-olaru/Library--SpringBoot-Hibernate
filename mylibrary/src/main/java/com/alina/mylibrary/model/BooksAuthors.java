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
    private Author authorId = new Author();


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_AB_ID"))
    private Book bookId = new Book();

    public BooksAuthors() {
    }


    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId= Author.get_copy(authorId);
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = Book.get_copy(bookId);
    }
}

