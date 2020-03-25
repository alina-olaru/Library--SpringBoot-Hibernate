package com.alina.mylibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BooksAuthors implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_AUTHOR_AB_ID"))
    private Author _authors;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_AB_ID"))
    private Book _booksA;

    public BooksAuthors() {
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksAuthors that = (BooksAuthors) o;
        return Objects.equals(_authors, that._authors) &&
                Objects.equals(_booksA, that._booksA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_authors, _booksA);
    }

    public Author get_authors() {
        return _authors;
    }

    public void set_authors(Author _authors) {
        this._authors = _authors;
    }

    public Book get_booksA() {
        return _booksA;
    }

    public void set_booksA(Book _booksA) {
        this._booksA = _booksA;
    }
}
