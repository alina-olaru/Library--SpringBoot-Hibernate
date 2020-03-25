package com.alina.mylibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BooksCategories implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_CATEGORIES__ID"))
    private Category categories;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_ID"))
    private Book booksC;

    public BooksCategories() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksCategories that = (BooksCategories) o;
        return Objects.equals(categories, that.categories) &&
                Objects.equals(booksC, that.booksC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, booksC);
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public Book getBooksC() {
        return booksC;
    }

    public void setBooksC(Book booksC) {
        this.booksC = booksC;
    }
}
