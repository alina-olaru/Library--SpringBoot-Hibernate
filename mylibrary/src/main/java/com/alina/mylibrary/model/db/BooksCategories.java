package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BooksCategoriesId.class)
public class BooksCategories implements Serializable {


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"booksCategories"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_CATEGORIES__ID"))
    private Category categories;


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"booksCategories", "reviews", "bookAuthor", "wishBooks", "persBooks" }, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_ID"))
    private Book booksC;


}
