package com.alina.mylibrary.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistId implements Serializable{

    private int bookwishlist;
    private int userwishlist;

}