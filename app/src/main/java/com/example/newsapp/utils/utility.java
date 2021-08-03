package com.example.newsapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class utility {
    public static boolean isInternetAvailable(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null){
            new AlertDialog.Builder(context)
                    .setTitle("JEE KOTA")
                    .setMessage("Internet connectivity not available !!")
                    .setPositiveButton("OK", null).show();
            return false;
        }
        return true;

    }
}
