package com.alina.mylibrary.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    @OneToMany(mappedBy = "categories",
            cascade = CascadeType.MERGE)
    private List<BooksCategories> booksCategories;

    @OneToMany(mappedBy = "category_voucher",
    fetch = FetchType.LAZY)
    private List<Voucher> category_vouchers;

}
