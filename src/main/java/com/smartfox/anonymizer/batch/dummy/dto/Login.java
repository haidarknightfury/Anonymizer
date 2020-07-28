package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {
    private String md5;
    private String password;
    private String salt;
    private String sha1;
    private String sha256;
    private String username;
    private String uuid;

    public Login() {

    }

    public String getMd5() {
        return this.md5;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getSha1() {
        return this.sha1;
    }

    public String getSha256() {
        return this.sha256;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "login{" + "username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", salt='" + this.salt + '\'' + ", md5='" + this.md5 + '\'' + ", sha1='" + this.sha1 + '\''
                + ", sha256='" + this.sha256 + '\'' + '}';
    }
}
