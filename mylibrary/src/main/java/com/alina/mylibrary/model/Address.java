package com.alina.mylibrary.model;

import javax.persistence.Column;

public class Address {

    @Column
    private String country;

    @Column
    private String province;

    @Column
    private String city;

    @Column
    private String streetName;

    @Column
    private String streetNumber;

    @Column
    private String block;

    @Column
    private String Floor;

    @Column
    private String Appartment_number;
}
