package com.example.newsapp.utils;

import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.view.menu.MenuBuilder;

import com.example.newsapp.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils  {

    private static ArrayList<News> mNewsList=new ArrayList<>();
   private static String TAG=QueryUtils.class.getSimpleName();


    public QueryUtils() {}


    public static List<News>  fetchNewsData(String requestUrl){
        URL url= createUrl(requestUrl);
        String jsonResponse=null;
        try {
            jsonResponse=makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(TAG,"ERROR IN GETTING JSON RESPONSE",e );
        }
        return extractNewsFromJson(jsonResponse);
    }
    public  static URL createUrl(String stringUrl){
        URL url=null;
        try {
            url=new URL(stringUrl);
        }
        catch (MalformedURLException e){
            Log.v(TAG,"ERROR WITH CREATING URL",e);
        }
        return url;
    }
    public static String makeHttpRequest(URL url) throws IOException{
         String jsonResponse="";
         if(url==null){
             return jsonResponse;
         }
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
                urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int code=urlConnection.getResponseCode();

        // If the request was successful (response code 200),
        // then read the input stream and parse the response.
        if (urlConnection.getResponseCode() == 200) {
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } else {
            Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
        }
    } catch (IOException e) {
        Log.e(TAG, "Problem retrieving the News Data JSON results.", e);
    } finally {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }
        return jsonResponse;
    }
public static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output=new StringBuilder();
        if(inputStream!=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
            return output.toString();


        }
        public static List<News> extractNewsFromJson(String newsJSON){
            if (TextUtils.isEmpty(newsJSON)) {
                return null;
            }
            // Create an empty ArrayList that we can start adding news to
        //    List<News> newsList = new ArrayList<>();

            // Try to parse the JSON response string. If there's a problem with the way the JSON
            // is formatted, a JSONException exception object will be thrown.
            try {
                // Create a JSONObject from the JSON response string
                JSONObject baseJsonResponse = new JSONObject(newsJSON);

                // Extract the JSONObject associated with the key called "response"
                JSONObject responseJsonObject = baseJsonResponse.getJSONObject(Constants.JSON_KEY_RESPONSE);

                // Extract the JSONArray associated with the key called "results"
                JSONArray resultsArray = responseJsonObject.getJSONArray(Constants.JSON_KEY_RESULTS);
                int size=resultsArray.length();
                // For each element in the resultsArray, create a {@link News} object
                         for(int i=0;i<size;i++){

                    // Get a single news at position i within the list of news
                    JSONObject currentNews = resultsArray.getJSONObject(i);
                    // For a given news, extract the val    ue for the key called "webTitle"
                    String webTitle = currentNews.getString(Constants.JSON_KEY_WEB_TITLE);
                    // For a given news, extract the value for the key called "sectionName"
                    String sectionName = currentNews.getString(Constants.JSON_KEY_SECTION_NAME);
                    // For a given news, extract the value for the key called "webPublicationDate"
                    String webPublicationDate = currentNews.getString(Constants.JSON_KEY_WEB_PUBLICATION_DATE);
                    // For a given news, extract the value for the key called "webUrl"
                    String webUrl = currentNews.getString(Constants.JSON_KEY_WEB_URL);

                    // For a given news, if it contains the key called "tags", extract JSONArray
                    // associated with the key "tags"
                    String author = null;
                    if (currentNews.has(Constants.JSON_KEY_TAGS)) {
                        // Extract the JSONArray associated with the key called "tags"
                        JSONArray tagsArray = currentNews.getJSONArray(Constants.JSON_KEY_TAGS);
                        if (tagsArray.length() != 0) {
                            // Extract the first JSONObject in the tagsArray
                            JSONObject firstTagsItem = tagsArray.getJSONObject(0);
                            // Extract the value for the key called "webTitle"
                            author = firstTagsItem.getString(Constants.JSON_KEY_WEB_TITLE);
                        }
                    }

                    // For a given news, if it contains the key called "fields", extract JSONObject
                    // associated with the key "fields"
                    String thumbnail = null;
                    String trailText = null;
                    if (currentNews.has(Constants.JSON_KEY_FIELDS)) {
                        // Extract the JSONObject associated with the key called "fields"
                        JSONObject fieldsObject = currentNews.getJSONObject(Constants.JSON_KEY_FIELDS);
                        // If there is the key called "thumbnail", extract the value for the key called "thumbnail"
                        if (fieldsObject.has(Constants.JSON_KEY_THUMBNAIL)) {
                            thumbnail = fieldsObject.getString(Constants.JSON_KEY_THUMBNAIL);
                        }
                        // If there is the key called "trailText", extract the value for the key called "trailText"
                        if (fieldsObject.has(Constants.JSON_KEY_TRAIL_TEXT)) {
                            trailText = fieldsObject.getString(Constants.JSON_KEY_TRAIL_TEXT);
                        }
                    }
                          //   string title, String section, String author, String date,String url, String thumbnail,String trailTex
                    // Create a new {@link News} object with the title and url from the JSON response.
                    News news = new News(webTitle, sectionName, author, webPublicationDate, webUrl, thumbnail, trailText);

                    // Add the new {@link News} to list of newsList.
                    mNewsList.add(news);
                }
            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e(TAG, "Problem parsing the news JSON results", e);
            }

            // Return the list of news
            return mNewsList;
        }




        }


