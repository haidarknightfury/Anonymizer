package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Registered {
    private int age;
    // declare instance variable
    private String date;

    public Registered() {

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
        return "registered{" + "date='" + this.date + '\'' + ", age='" + this.age + '\'' + '}';
    }
}
