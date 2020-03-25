package com.alina.mylibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Wishlist implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_WISHLIST_ID"))
    private Book bookwishlist;

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_WISHLIST_ID"))
    private BookUser userwishlist;



    public Book getBookwishlist() {
        return bookwishlist;
    }

    public void setBookwishlist(Book bookwishlist) {
        this.bookwishlist = bookwishlist;
    }

    public BookUser getUserwishlist() {
        return userwishlist;
    }

    public void setUserwishlist(BookUser userwishlist) {
        this.userwishlist = userwishlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(bookwishlist, wishlist.bookwishlist) &&
                Objects.equals(userwishlist, wishlist.userwishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookwishlist, userwishlist);
    }
}
