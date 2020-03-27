package com.alina.mylibrary.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class WishlistId  implements Serializable {

    private int bookwishlist;
    private int userwishlist;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistId that = (WishlistId) o;
        return bookwishlist == that.bookwishlist &&
                userwishlist == that.userwishlist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookwishlist, userwishlist);
    }

    public WishlistId(int bookwishlist, int userwishlist) {
        this.bookwishlist = bookwishlist;
        this.userwishlist = userwishlist;
    }

    public int getBookwishlist() {
        return bookwishlist;
    }

    public void setBookwishlist(int bookwishlist) {
        this.bookwishlist = bookwishlist;
    }

    public int getUserwishlist() {
        return userwishlist;
    }

    public void setUserwishlist(int userwishlist) {
        this.userwishlist = userwishlist;
    }
}
