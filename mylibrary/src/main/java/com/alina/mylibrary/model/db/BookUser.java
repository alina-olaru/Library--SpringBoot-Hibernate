package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Email
    private String emailAdress;

    @Column(length = 11, unique = true)
    @NotNull
    private String phoneNumber;

    @Column
    @NotNull
    private boolean newsletter = false;

    @Column
    @NotNull
    private boolean adminPrivilege = false;

    @Column
    @NotNull
    private boolean userPrivilege = false;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private Boolean blocked = false;

    @Column
    private Boolean isEnabled;

    @Column
    @ColumnDefault("0")
    private Double bonus;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"user"}, allowSetters = true)
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<PersonalBook> persBooks;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userwishlist"}, allowSetters = true)
    @OneToMany(mappedBy = "userwishlist")
    private List<Wishlist> wishBooks;




    @JsonIgnoreProperties(ignoreUnknown=true, value = {"ordersUser"}, allowSetters = true)
    @OneToMany(mappedBy = "ordersUser",
            fetch = FetchType.LAZY)
    private List<BookOrder> ordersbyuser;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"com"}, allowSetters = true)
    @OneToMany(mappedBy = "com",
            fetch = FetchType.LAZY)
    private List<Complaint> userComplaints;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userReviewMaker"}, allowSetters = true)
    @OneToMany(mappedBy = "userReviewMaker",
            fetch = FetchType.LAZY)
    private List<Review> reviewsByUser;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"usersWithVouchers"}, allowSetters = true)
    @OneToMany(mappedBy = "usersWithVouchers",
            fetch = FetchType.LAZY)
    private List<VoucherUser> userVoucherLink;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userAddress"}, allowSetters = true)
    @OneToMany(mappedBy = "userAddress")
    private List<Address> addresses;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userForQuizz"}, allowSetters = true)
    @OneToMany(mappedBy = "userForQuizz")
    private List<QuizzUser> userQuizzes;

    @Override
    public String toString() {
        return " ";
    }
}

