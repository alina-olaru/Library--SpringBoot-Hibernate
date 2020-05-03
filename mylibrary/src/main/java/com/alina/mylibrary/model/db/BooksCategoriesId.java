package com.alina.mylibrary.model.db;


import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class BooksCategoriesId implements Serializable {
    private int categories;
    private int booksC;


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksCategoriesId that = (BooksCategoriesId) o;
        return categories == that.categories &&
                booksC == that.booksC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, booksC);
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public int getBooksC() {
        return booksC;
    }

    public void setBooksC(int booksC) {
        this.booksC = booksC;
    }
}
