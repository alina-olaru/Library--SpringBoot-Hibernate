package com.alina.mylibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
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

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                Objects.equals(categoryTitle, category.categoryTitle) &&
                Objects.equals(categoryDescription, category.categoryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryTitle, categoryDescription);
    }


    @OneToMany(mappedBy = "categories",
    cascade = CascadeType.ALL)
    private Set<BooksCategories> booksCategories;

}
