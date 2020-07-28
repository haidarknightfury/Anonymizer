package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    private String first;
    private String last;
    // declare IV
    private String title;

    public Name() {

    }

    public String getFirst() {
        return this.first;
    }

    public String getLast() {
        return this.last;
    }

    public String getTitle() {
        return this.title;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // override
    @Override
    public String toString() {
        return "Name{" + "title='" + this.title + '\'' + ", first='" + this.first + '\'' + ", last='" + this.last + '\'' + '}';
    }
}
