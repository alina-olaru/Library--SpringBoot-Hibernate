package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table
public class Publisher {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;


    @NotNull
    @Column
    private String publisherTitle;


    @OneToMany(mappedBy = "publisher")
    private List<Book> book;



    public Publisher(String publisherTitle) {
        this.publisherTitle = publisherTitle;
    }


    public Publisher(){

    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherTitle() {
        return publisherTitle;
    }

    public void setPublisherTitle(String publisherTitle) {
        this.publisherTitle = publisherTitle;
    }


    @OneToOne(mappedBy = "publisher_voucher")
    private Voucher voucher_with_a_publisher;

}
