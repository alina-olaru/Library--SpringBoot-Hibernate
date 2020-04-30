package com.alina.mylibrary.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(length = 50)
    @NotNull
    private String bookTitle;

    @Column
    private int bookYear;

    @Column
    private int numberOfPages;

    @Column
    private int numberofVolumes;

    @Column(length = 20)
    private String bookLanguage;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String bookDescription;

    @Column(length = 60)
    private String bookDimension;

    @Column
    private float bookWeight;

    @Column
    @NotNull
    private float bookPrice;

    @Column
    @NotNull
    private String coverType;

    //TODO poate de adaugat sale pt eventuala eticheta

    @Column
    private int numberOfReviews;

    @Column
    private float bookRating;

    @Column
    @NotNull
    private int numberOfBoooks;

    @Column
    @Lob
    private Blob bookImage;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_publisher_id"))
    private Publisher publisher;
    @OneToMany(mappedBy = "book",
            cascade = CascadeType.MERGE)
    private List<PersonalBook> persBooks;
    @OneToMany(mappedBy = "bookwishlist",
            cascade = CascadeType.MERGE)
    private List<Wishlist> wishBooks;
    @OneToMany(mappedBy = "booksorder",
            cascade = CascadeType.MERGE)
    private List<OrderItem> items;
    @OneToMany(mappedBy = "booksC",
            cascade = { CascadeType.ALL },
            orphanRemoval = true)
    private List<BooksCategories> booksCategories;
    @OneToMany(mappedBy = "bookId",
            cascade = { CascadeType.ALL},
            orphanRemoval = true)
    private List<BooksAuthors> bookAuthor;
    @OneToMany(mappedBy = "bookR")
    private List<Review> reviews;

    public static Book get_copy(Book old_book) {
        Book new_book = new Book();
        new_book.setPublisher(old_book.getPublisher());
        new_book.setBookAuthor(old_book.getBookAuthor());
        new_book.setBookDescription(old_book.getBookDescription());
        new_book.setNumberofVolumes(old_book.getNumberofVolumes());
        new_book.setNumberOfPages(old_book.getNumberOfPages());
        new_book.setNumberOfReviews(old_book.getNumberOfReviews());
        new_book.setCoverType(old_book.getCoverType());
        new_book.setNumberOfBoooks(old_book.getNumberOfBoooks());
        new_book.setBookYear(old_book.getBookYear());
        new_book.setBookWeight(old_book.getBookWeight());
        new_book.setBookTitle(old_book.getBookTitle());
        new_book.setBookRating(old_book.getBookRating());
        new_book.setBookPrice(old_book.getBookPrice());
        new_book.setBookLanguage(old_book.getBookLanguage());
        new_book.setBookDimension(old_book.getBookDimension());
        new_book.setBookId(old_book.getBookId());
        new_book.setBooksCategories(old_book.getBooksCategories());
        new_book.setItems(old_book.getItems());
        new_book.setPersBooks(old_book.getPersBooks());
        new_book.setWishBooks(old_book.getWishBooks());
        return new_book;
        //todo de adaugat campurile noi sau de schimbat cu seterele
    }
}

