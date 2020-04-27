package com.alina.mylibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int addressId;

    @NotNull
    @Column(length = 20)
    private String country;

    @NotNull
    @Column(length = 20)
    private String province;

    @NotNull
    @Column(length = 20)
    private String city;

    @NotNull
    @Column(length = 20)
    private String streetName;

    @NotNull
    @Column
    private int streetNumber;

    @NotNull
    @Column(length = 20)
    private String block;

    @NotNull
    @Column
    private int floor;

    @NotNull
    @Column
    private int appartmentNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ADDRESS_BOOK_USER"))
    private BookUser userAddress;
}
