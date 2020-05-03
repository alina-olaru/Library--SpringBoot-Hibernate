package com.alina.mylibrary.model.db;


import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class BooksAuthorsId implements Serializable {
    private int authorId;
    private int bookId;

    public BooksAuthorsId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksAuthorsId that = (BooksAuthorsId) o;
        return authorId == that.authorId &&
                bookId == that.bookId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }
}