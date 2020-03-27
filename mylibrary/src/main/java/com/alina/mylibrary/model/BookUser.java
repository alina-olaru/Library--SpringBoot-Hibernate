package com.alina.mylibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(length = 30, unique = true)
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

    public BookUser() {
    }

    public BookUser(@NotNull String firstName, @NotNull String lastName, @NotNull String emailAdress, @NotNull String phoneNumber, @NotNull boolean newsletter, @NotNull boolean adminPrivilege, @NotNull boolean userPrivilege, @NotNull String country, @NotNull String province, @NotNull String city, @NotNull String streetName, @NotNull int streetNumber, @NotNull String block, @NotNull int floor, @NotNull int appartmentNumber, String password, String username, Set<PersonalBook> persBooks, Set<Wishlist> wishBooks, Set<BookOrder> ordersbyuser, Set<Complaint> userComplaints, Set<Review> reviewsByUser, Set<VoucherUser> userVoucherLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.newsletter = newsletter;
        this.adminPrivilege = adminPrivilege;
        this.userPrivilege = userPrivilege;
        this.country = country;
        this.province = province;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.block = block;
        this.floor = floor;
        this.appartmentNumber = appartmentNumber;
        this.password = password;
        this.username = username;
        this.persBooks = persBooks;
        this.wishBooks = wishBooks;
        this.ordersbyuser = ordersbyuser;
        this.userComplaints = userComplaints;
        this.reviewsByUser = reviewsByUser;
        this.userVoucherLink = userVoucherLink;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public boolean isAdminPrivilege() {
        return adminPrivilege;
    }

    public void setAdminPrivilege(boolean adminPrivilege) {
        this.adminPrivilege = adminPrivilege;
    }

    public boolean isUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(boolean userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getAppartmentNumber() {
        return appartmentNumber;
    }

    public void setAppartmentNumber(int appartmentNumber) {
        this.appartmentNumber = appartmentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<PersonalBook> getPersBooks() {
        return persBooks;
    }

    public void setPersBooks(Set<PersonalBook> persBooks) {
        this.persBooks = persBooks;
    }

    public Set<Wishlist> getWishBooks() {
        return wishBooks;
    }

    public void setWishBooks(Set<Wishlist> wishBooks) {
        this.wishBooks = wishBooks;
    }

    public Set<BookOrder> getOrdersbyuser() {
        return ordersbyuser;
    }

    public void setOrdersbyuser(Set<BookOrder> ordersbyuser) {
        this.ordersbyuser = ordersbyuser;
    }

    public Set<Complaint> getUserComplaints() {
        return userComplaints;
    }

    public void setUserComplaints(Set<Complaint> userComplaints) {
        this.userComplaints = userComplaints;
    }

    public Set<Review> getReviewsByUser() {
        return reviewsByUser;
    }

    public void setReviewsByUser(Set<Review> reviewsByUser) {
        this.reviewsByUser = reviewsByUser;
    }

    public Set<VoucherUser> getUserVoucherLink() {
        return userVoucherLink;
    }

    public void setUserVoucherLink(Set<VoucherUser> userVoucherLink) {
        this.userVoucherLink = userVoucherLink;
    }

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL)
    private  Set<PersonalBook> persBooks;


    @OneToMany(mappedBy = "userwishlist",
    cascade = CascadeType.ALL)
    //TODO s-a rezolvat problema,dar e ok pt lunga durata?
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

