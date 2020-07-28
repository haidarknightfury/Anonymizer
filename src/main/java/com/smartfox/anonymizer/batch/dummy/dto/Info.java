package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    private int page;
    private int results;
    // declare instance variable
    private String seed;
    private String version;

    public Info() {

    }

    public int getPage() {
        return this.page;
    }

    public int getResults() {
        return this.results;
    }

    public String getSeed() {
        return this.seed;
    }

    public String getVersion() {
        return this.version;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "info{" + "seed='" + this.seed + '\'' + ", results='" + this.results + '\'' + ", page='" + this.page + '\'' + ", version='" + this.version + '\'' + '}';
    }
}
