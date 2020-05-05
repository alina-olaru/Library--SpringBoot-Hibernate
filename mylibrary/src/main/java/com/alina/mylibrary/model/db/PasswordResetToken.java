package com.alina.mylibrary.model.db;

import javax.persistence.*;
import java.util.Date;

@Entity

public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = BookUser.class, fetch = FetchType.EAGER)
    @JoinColumn
    private BookUser user;

    private Date expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, BookUser user) {
        this.token = token;
        this.user = user;
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BookUser getUser() {
        return user;
    }

    public void setUser(BookUser user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}