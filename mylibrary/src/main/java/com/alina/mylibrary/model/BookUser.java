package com.alina.mylibrary.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class BookUser {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(length = 20)
    @NotNull
    private String firstName;

    @Column(length = 20)
    @NotNull
    private String lastName;

    @Column(length = 20)
    @NotNull
    @UniqueElements
    private String emailAdress;

    @Column(length = 11,unique = true)
    //TODO DE CE NU MERGE UNIQUE
    @NotNull
    private String phoneNumber;

    @Column
    @NotNull
    @ColumnDefault("false")
    private boolean newsletter=false;


    @Column
    @ColumnDefault("false")
    @NotNull
    private boolean adminPrivilege=false;


    @Column
    @ColumnDefault("false")
    @NotNull
    private boolean userPrivilege =false;

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

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL)
    private  Set<PersonalBook> persBooks;

    @OneToMany(mappedBy = "userwishlist",
    cascade = CascadeType.ALL)
    private Set<Wishlist> wishBooks;


    @OneToMany(mappedBy = "ordersUser",
    cascade = CascadeType.ALL)
    private Set<BookOrder> ordersbyuser;


    @OneToMany(mappedBy = "com")
    private Set<Complaint> userComplaints;
}
