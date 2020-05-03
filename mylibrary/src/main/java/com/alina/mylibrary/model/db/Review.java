package com.alina.mylibrary.model.db;


import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String textReview;


    @Column
    @Lob
    private byte[] reviewPhotoDb;

    @Transient
    private String reviewPhoto;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"reviews"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_REVIEW_ID"))
    private Book bookR;


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"reviewsByUser"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_REVIEW_ID"))
    private BookUser userReviewMaker;
}
