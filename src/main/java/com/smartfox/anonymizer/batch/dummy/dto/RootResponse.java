package com.smartfox.anonymizer.batch.dummy.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    Info info;

    List<Results> results;

    public Info getInfo() {
        return this.info;
    }

    public List<Results> getResults() {
        return this.results;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "RootResponse [results=" + this.results + ", info=" + this.info + "]";
    }

}
