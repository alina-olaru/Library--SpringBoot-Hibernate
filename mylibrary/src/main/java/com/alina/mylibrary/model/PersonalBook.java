package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class PersonalBook implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private BookUser user;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_BOOK_ID"))
    private Book book;


    @NotNull
    @Column(length = 40)
    private String persBAuthor;

    @NotNull
    @Column(length = 40)
    private String persBTitle;


    public PersonalBook() {
    }

    public String getPersBAuthor() {
        return persBAuthor;
    }

    public void setPersBAuthor(String persBAuthor) {
        this.persBAuthor = persBAuthor;
    }

    public String getPersBTitle() {
        return persBTitle;
    }

    public void setPersBTitle(String persBTitle) {
        this.persBTitle = persBTitle;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalBook that = (PersonalBook) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }

    public BookUser getUser() {
        return user;
    }

    public void setUser(BookUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}