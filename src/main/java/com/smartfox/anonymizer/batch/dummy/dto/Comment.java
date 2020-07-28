package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Comment() {

    }

    @Override
    public String toString() {
        return "Lorem Ipsum bla bla bla";
    }

}
