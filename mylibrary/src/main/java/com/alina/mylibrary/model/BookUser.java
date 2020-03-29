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
import java.util.List;
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
    @NotNull
    private String phoneNumber;

    @Column
    @NotNull
    private boolean newsletter=false;


    @Column
    @NotNull
    private boolean adminPrivilege=false;


    @Column
    @NotNull
    private boolean userPrivilege =false;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private Boolean blocked=false;

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL)
    private List<PersonalBook> persBooks;

    @OneToMany(mappedBy = "userwishlist",
    cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Wishlist> wishBooks;

    @OneToMany(mappedBy = "ordersUser",
    cascade = CascadeType.ALL)
    private List<BookOrder> ordersbyuser;

    @OneToMany(mappedBy = "com")
    private List<Complaint> userComplaints;

    @OneToMany(mappedBy = "userReviewMaker")
    private List<Review> reviewsByUser;

    @OneToMany(mappedBy = "usersWithVouchers",
            cascade = CascadeType.ALL)
    private List<VoucherUser> userVoucherLink;

    @OneToMany(mappedBy = "userAddress",
    cascade = CascadeType.ALL)
    private List<Address> addresses;

}

