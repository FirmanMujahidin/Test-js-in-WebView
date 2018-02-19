package com.firmanmujahidin.testinterview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TestActivity extends AppCompatActivity {

    public WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mWebView = (WebView) findViewById(R.id.mWebView);
        setmWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setmWebView(){

        WebSettings webSettings =   mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(Uri.parse("file:///android_asset/index.html").toString());
    }
}
