package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebViewActivity extends AppCompatActivity {


     WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.web_view);
          webView.getSettings().setJavaScriptEnabled(true);


        final ProgressDialog p= new ProgressDialog(this);
        p.setMessage("file is opening....!!");

        String urlstring=null;
        if(getIntent()!=null){
            urlstring= getIntent().getStringExtra("muri");
        }
        else{
            Toast.makeText(this,"netowrk problem",Toast.LENGTH_LONG).show();
        }
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url)
                ;
                p.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                p.show();

            }
        });

     /**   String murl="";
        try {
            murl= URLEncoder.encode(urlstring,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
      */
        webView.loadUrl(urlstring);
       // webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+murl);
    }
}