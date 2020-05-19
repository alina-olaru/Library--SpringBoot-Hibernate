package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@IdClass(WishlistId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"wishBooks", "bookAuthor", "booksCategories", "persBooks" }, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_WISHLIST_ID"))
    private Book bookwishlist;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"wishBooks"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_WISHLIST_ID"))
    private BookUser userwishlist;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" +
                '}';
    }

}
