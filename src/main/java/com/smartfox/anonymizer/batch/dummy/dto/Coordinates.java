package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    // declaring variables
    private double latitude;
    private double longitude;

    public Coordinates() {

    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "coordinates{" + "latitude='" + this.latitude + '\'' + ", longitude='" + this.longitude + '\'' + '}';
    }
}
