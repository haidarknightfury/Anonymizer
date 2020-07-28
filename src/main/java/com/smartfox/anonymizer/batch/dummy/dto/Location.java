package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String city;
    private Coordinates coordinates;
    private String postcode;
    private String state;
    private String street;
    private Timezone timezone;

    public Location() {

    }

    public String getCity() {
        return this.city;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getState() {
        return this.state;
    }

    public String getStreet() {
        return this.street;
    }

    public Timezone getTimezone() {
        return this.timezone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    // override
    @Override
    public String toString() {
        return "Location{" + "street='" + this.street + '\'' + ", city" + this.city + '\'' + ", state='" + this.state + '\'' + ", postcode='" + this.postcode + '\'' + ", coordinates='"
                + this.coordinates + '\'' + ", timezone='" + this.timezone + '\'' + '}';
    }
}
