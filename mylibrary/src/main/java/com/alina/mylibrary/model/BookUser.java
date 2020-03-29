package com.alina.mylibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(length = 50, unique = true)
    @NotNull
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

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private Boolean blocked=false;

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL)
    private  Set<PersonalBook> persBooks;

    @OneToMany(mappedBy = "userwishlist",
    cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Wishlist> wishBooks;

    @OneToMany(mappedBy = "ordersUser",
    cascade = CascadeType.ALL)
    private Set<BookOrder> ordersbyuser;

    @OneToMany(mappedBy = "com")
    private Set<Complaint> userComplaints;

    @OneToMany(mappedBy = "userReviewMaker")
    private Set<Review> reviewsByUser;

    @OneToMany(mappedBy = "usersWithVouchers",
            cascade = CascadeType.ALL)
    private Set<VoucherUser> userVoucherLink;

}

