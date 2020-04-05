package com.alina.mylibrary.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BooksCategoriesId.class)
public class BooksCategories implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_CATEGORIES__ID"))
    private Category categories;


    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOKS_ID"))
    private Book booksC;


}
