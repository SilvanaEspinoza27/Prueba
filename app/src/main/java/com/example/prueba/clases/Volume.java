package com.example.prueba.clases;

public class Volume {
    private String issueId;
    private String volume;
    private String number;
    private String year;
    private String datePublished;
    private String title;
    private String doi;
    private String cover;

    // Constructor, getters, and setters
    public Volume(String issueId, String volume, String number, String year, String datePublished, String title, String doi, String cover) {
        this.issueId = issueId;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.datePublished = datePublished;
        this.title = title;
        this.doi = doi;
        this.cover = cover;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getVolume() {
        return volume;
    }

    public String getNumber() {
        return number;
    }

    public String getYear() {
        return year;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getTitle() {
        return title;
    }

    public String getDoi() {
        return doi;
    }

    public String getCover() {
        return cover;
    }
}
