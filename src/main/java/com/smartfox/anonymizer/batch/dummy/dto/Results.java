package com.smartfox.anonymizer.batch.dummy.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cell;
    private Dob dob;
    private String email;
    // declaring instance variable
    private String gender;
    private Id id;
    private Location location;
    private Login login;
    private Name name;
    private String nat;
    private String phone;
    private Picture picture;
    private Registered registered;

    public Results() {

    }

    public String getCell() {
        return this.cell;
    }

    public Dob getDob() {
        return this.dob;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGender() {
        return this.gender;
    }

    public Id getId() {
        return this.id;
    }

    public Location getLocation() {
        return this.location;
    }

    public Login getLogin() {
        return this.login;
    }

    public Name getName() {
        return this.name;
    }

    public String getNat() {
        return this.nat;
    }

    public String getPhone() {
        return this.phone;
    }

    public Picture getPicture() {
        return this.picture;
    }

    public Registered getRegistered() {
        return this.registered;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "results{" + "gender='" + this.gender + '\'' + ", name='" + this.name + '\'' + ", location='" + this.location + '\'' + ", email='" + this.email + '\'' + ", login='" + this.login + '\''
                + ", dob='" + this.dob + '\'' + ", registered='" + this.registered + '\'' + ", phone='" + this.phone + '\'' + ", cell='" + this.cell + '\'' + ", id='" + this.id + '\'' + ", picture='"
                + this.picture + '\'' + ", nat='" + this.nat + '\'' + '}';
    }
}
