package com.alina.mylibrary.model.db;


import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @Column
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int reviewID;

    @NotNull
    @Column
    private String reviewerName;

    @NotNull
    @Column
    private Double value;

    @NotNull
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String textReview;


    @Column
    @Lob
    private byte[] reviewPhotoDb;

    @Transient
    private String reviewPhoto;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"reviews", "persBooks", "wishBooks", "items", "bookAuthor", "booksCategories" }, allowSetters = true)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_REVIEW_ID"))
    private Book bookR;


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"reviewsByUser", "persBooks", "wishBooks", "ordersbyuser", "userComplaints", "userVoucherLink", "addresses"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_REVIEW_ID"))
    private BookUser userReviewMaker;

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewID == review.reviewID &&
                Objects.equals(reviewerName, review.reviewerName) &&
                Objects.equals(value, review.value) &&
                Objects.equals(textReview, review.textReview) &&
                Arrays.equals(reviewPhotoDb, review.reviewPhotoDb) &&
                Objects.equals(reviewPhoto, review.reviewPhoto) &&
                Objects.equals(bookR, review.bookR) &&
                Objects.equals(userReviewMaker, review.userReviewMaker);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(reviewID, reviewerName, value, textReview, reviewPhoto, bookR, userReviewMaker);
        result = 31 * result + Arrays.hashCode(reviewPhotoDb);
        return result;
    }
}
