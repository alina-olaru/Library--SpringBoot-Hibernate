package com.alina.mylibrary.model.db;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotNull
    @Column
    private String categoryTitle;


    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String categoryDescription;

    @ToString.Exclude
    @JsonIgnoreProperties(ignoreUnknown=true, value = {"categories"}, allowSetters = true)
    @OneToMany(mappedBy = "categories",
            fetch = FetchType.LAZY)
    private List<BooksCategories> booksCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "category_voucher",
            fetch = FetchType.LAZY)
    private List<Voucher> category_vouchers;

}
