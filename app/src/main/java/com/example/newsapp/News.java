package com.example.newsapp;

public class News {

    /** Title of the article */
    private  String mTitle;     //title

    /** Section name of the article*/
    private String mSection;      // sport// business//

    /** Author name in the article */
    private String mAuthor;   //author

    /** Web publication date of the article */
    private  String mDate;     //date

    /** Website URL f the article */
    private String mUrl;           // link

    /** Thumbnail of the article */
   private String mThumbnail;   // image

    private String mTrailTextHtml;    //description


    public News(String webTitle, String section, String author, String date,String url, String thumbnail,String trailText) {
        mTitle = webTitle;
        mSection = section;
        mAuthor = author;
        mDate = date;
       mUrl = url;
        mThumbnail = thumbnail;
        mTrailTextHtml = trailText;
    }

    public  String getTitle() {
        return mTitle;
    } //article--->title


    public  String getSection() {
        return mSection;
    }  //  unknown


    public  String getAuthor() {
        return mAuthor;
    }   //  article--->author

    public  String getDate() {
        return mDate;
    }     //articles--->publishedAt

public String getUrl() {
      return mUrl;}                 // articles--->url


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
