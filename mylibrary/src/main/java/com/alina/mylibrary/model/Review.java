package com.alina.mylibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

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
}
