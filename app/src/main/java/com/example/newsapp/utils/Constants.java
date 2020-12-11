package com.example.newsapp.utils;

public class Constants {

 private Constants() {
 }


 /**
  * Constants value for each fragment
  */
 public static final int HOME = 0;
 //  public static final int WORLD = 1;
 //public static final int SCIENCE = 2;
 public static final int SPORT = 2;
 // public static final int ENVIRONMENT = 4;
 //public static final int SOCIETY = 5;
 // public static final int FASHION = 6;
 public static final int BUSINESS = 1;
 //public static final int CULTURE = 8;

 //  public static final String NEWS_REQUEST_URL="https://newsapi.org/v2/everything?domains=indianexpress.com&q=business&apiKey=a1307f33512645af9d6c065510b5ed5a";

 /**
  * Extract the key associated with the JSONObject
  */
 static final String JSON_KEY_RESPONSE = "response";
 static final String JSON_KEY_RESULTS = "results";
 static final String JSON_KEY_WEB_TITLE = "webTitle";
 static final String JSON_KEY_SECTION_NAME = "sectionName";
 static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
 static final String JSON_KEY_WEB_URL = "webUrl";
 static final String JSON_KEY_TAGS = "tags";
 static final String JSON_KEY_FIELDS = "fields";
 static final String JSON_KEY_THUMBNAIL = "thumbnail";
 static final String JSON_KEY_TRAIL_TEXT = "trailText";

 /**
  * Read timeout for setting up the HTTP request
  */
 static final int READ_TIMEOUT = 10000; /* milliseconds */

 /**
  * Connect timeout for setting up the HTTP request
  */
 static final int CONNECT_TIMEOUT = 15000; /* milliseconds */

 /**
  * HTTP response code when the request is successful
  */
 static final int SUCCESS_RESPONSE_CODE = 200;

 /**
  * Request method type "GET" for reading information from the server
  */
 static final String REQUEST_METHOD_GET = "GET";

 /**
  * URL for news data from the guardian data set
  */
 // public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";

//public static final String NEWS_REQUEST_URL="https://content.guardianapis.com/search?q=12%20years%20a%20slave&format=json&tag=film/film,tone/reviews&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=relevance&api-key=test";


 public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?show-fields=trailText,headline,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";
}