package com.example.newsapp.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.newsapp.News;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static String TAG=NewsLoader.class.getSimpleName();
    private String mUrl;

    public NewsLoader(@NonNull Context context,String url) {
        super(context);
        mUrl=url;
    }



    @Override
    public List<News> loadInBackground() {
         if(mUrl==null){
             return null;
         }
         else{
             List<News> newsData=QueryUtils.fetchNewsData(mUrl);
             return newsData;
         }

    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }


}
