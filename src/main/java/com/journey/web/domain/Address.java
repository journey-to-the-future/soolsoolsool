package com.journey.web.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address extends BaseEntity {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
