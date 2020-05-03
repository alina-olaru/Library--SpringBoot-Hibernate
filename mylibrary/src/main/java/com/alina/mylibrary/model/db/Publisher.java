package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;

    @NotNull
    @Column
    private String publisherTitle;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"publisher"}, allowSetters = true)
    @OneToMany(mappedBy = "publisher",
    fetch = FetchType.LAZY)
    private List<Book> book;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher_voucher",
    fetch = FetchType.LAZY)
    private List<Voucher> publisher_vouchers;

}
