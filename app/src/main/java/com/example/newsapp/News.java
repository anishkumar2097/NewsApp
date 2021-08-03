package com.example.newsapp;

public class News {


    private String mTitle;       //title
    private String mSection;      // sport// business//
    private String mAuthor;      //author
    private String mDate;     //date
    private String mUrl;           // link
    private String mThumbnail;   // image
    private String mTrailTextHtml;    //description


    public News(String webTitle, String section, String author, String date, String url, String thumbnail, String trailText) {
        mTitle = webTitle;
        mSection = section;
        mAuthor = author;
        mDate = date;
        mUrl = url;
        mThumbnail = thumbnail;
        mTrailTextHtml = trailText;
    }

    public String getTitle() {
        return mTitle;
    } //article--->title


    public String getSection() {
        return mSection;
    }  //  unknown


    public String getAuthor() {
        return mAuthor;
    }   //  article--->author

    public String getDate() {
        return mDate;
    }     //articles--->publishedAt

    public String getUrl() {
        return mUrl;
    }                 // articles--->url


    public void setSection(String mSection) {
        this.mSection = mSection;
    }

    public String getThumbnail() {
        return mThumbnail;
    }


    public String getTrailTextHtml() {
        return mTrailTextHtml;
    }


}
