package com.alina.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@IdClass(WishlistId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_WISHLIST_ID"))
    private Book bookwishlist;

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_WISHLIST_ID"))
    private BookUser userwishlist;

}
