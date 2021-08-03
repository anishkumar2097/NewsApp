package com.example.newsapp.utils;

public class Constants {

 private Constants() {
 }



 public static final int HOME = 0;
 public static final int SPORT = 2;
 public static final int BUSINESS = 1;

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


 static final int READ_TIMEOUT = 10000;
 static final int CONNECT_TIMEOUT = 15000;
 static final int SUCCESS_RESPONSE_CODE = 200;
 static final String REQUEST_METHOD_GET = "GET";
 public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?show-fields=trailText,headline,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";
}