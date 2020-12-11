package com.example.newsapp;

public class News {

    /** Title of the article */
    private static String mTitle;     //title

    /** Section name of the article*/
    private static String mSection;      // sport// business//

    /** Author name in the article */
    private static String mAuthor;   //author

    /** Web publication date of the article */
    private static String mDate;     //date

    /** Website URL of the article */
    private String mUrl;           // link

    /** Thumbnail of the article */
   private String mThumbnail;   // image

    /** TrailText of the article with string type Html */
    private String mTrailTextHtml;    //description

    /**
     * Constructs a new {@link News} object.
     *
     * @param title is the title of the article
     * @param section is the section name of the article
     * @param author is author name in article
     * @param date is the web publication date of the article
  //   * @param url is the website URL to find more details about the article
  //   * @param thumbnail is the thumbnail of the article
    // * @param trailText is trail text of the article with string type Html
     */
    public News(String title, String section, String author, String date,String url, String thumbnail,String trailText) {
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
       mUrl = url;
        mThumbnail = thumbnail;
        mTrailTextHtml = trailText;
    }

    /**
     * Returns the title of the article
     */
    public  String getTitle() {
        return mTitle;
    } //article--->title

    /**
     * Returns the section name of the article.
     */
    public  String getSection() {
        return mSection;
    }  //  unknown

    /**
     * Returns the author name of the article.
     */
    public  String getAuthor() {
        return mAuthor;
    }   //  article--->author
    /**
     * Returns the web publication date of the article.
     */
    public  String getDate() {
        return mDate;
    }     //articles--->publishedAt

    /**
     * Returns the website URL to find more information about the news.
     */
public String getUrl() {
      return mUrl;}                 // articles--->url
  /**  }

    /**
     * Returns the thumbnail of the article
     */
    public String getThumbnail() {
       return mThumbnail;
    }

    /**
     * Returns the TrailText of the article with string type Html
     */
    public String getTrailTextHtml() {
       return mTrailTextHtml;
    }

}
