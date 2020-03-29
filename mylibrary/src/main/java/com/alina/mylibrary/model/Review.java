package com.alina.mylibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table

public class Review {

    @Id
    @Column
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int reviewID;

    @NotNull
    @Column
    private String reviewerName;

    @NotNull
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String textReview;


    @Column
    @Lob
    private Blob reviewPhoto;

    public Review() {
    }



    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public Blob getReviewPhoto() {
        return reviewPhoto;
    }

    public void setReviewPhoto(Blob reviewPhoto) {
        this.reviewPhoto = reviewPhoto;
    }


    public Book getBookR() {
        return bookR;
    }

    public void setBookR(Book bookR) {
        this.bookR = bookR;
    }

    public BookUser getUserReviewMaker() {
        return userReviewMaker;
    }

    public void setUserReviewMaker(BookUser userReviewMaker) {
        this.userReviewMaker = userReviewMaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewID == review.reviewID &&
                reviewerName.equals(review.reviewerName) &&
                textReview.equals(review.textReview) &&
                Objects.equals(reviewPhoto, review.reviewPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewID, reviewerName, textReview, reviewPhoto);
    }

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_REVIEW_ID"))
    private Book bookR;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_REVIEW_ID"))
    private BookUser userReviewMaker;
}
