package com.alina.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long tokenid;

    @Column
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = BookUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private BookUser user;

    public ConfirmationToken(BookUser user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    // getters and setters
}