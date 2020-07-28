package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dob {
    private int age;
    private String date;

    public Dob() {

    }

    public int getAge() {
        return this.age;
    }

    public String getDate() {
        return this.date;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "dob{" + "date='" + this.date + '\'' + ", age='" + this.age + '\'' + '}';
    }
}
