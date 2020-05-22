package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Entity
@Table
@IdClass(PersonalBookId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalBook implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"persBooks"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private BookUser user;


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"persBooks"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_BOOK_ID"))
    private Book book;


    @NotNull
    @Column(length = 40)
    private String persBAuthor;

    @NotNull
    @Column(length = 40)
    private String persBTitle;

    @Column
    @Lob
    private byte[] persBImageDb;

}