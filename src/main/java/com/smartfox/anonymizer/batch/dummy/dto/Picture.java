package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {
    // declare instance variable
    private String large;
    private String medium;
    private String thumbnail;

    public Picture() {

    }

    public String getLarge() {
        return this.large;
    }

    public String getMedium() {
        return this.medium;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "picture{" + "large='" + this.large + '\'' + ", medium='" + this.medium + '\'' + ", thumbnail='" + this.thumbnail + '\'' + '}';
    }
}
