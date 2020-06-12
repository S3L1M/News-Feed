package com.example.newsfeed;

public class News {
    private String mHeadline = "";
    private String mAuthor = "";
    private String mDate = "";
    private String mSection = "";
    private String mUrl;

    public News(String headline, String author, String section, String date, String url) {
        mHeadline = headline;
        mAuthor = author;
        mSection = section;
        mDate = date;
        mUrl = url;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getSection() {
        return mSection;
    }

    public String getUrl() {
        return mUrl;
    }
}
