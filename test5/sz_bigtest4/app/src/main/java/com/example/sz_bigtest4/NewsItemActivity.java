package com.example.sz_bigtest4;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewsItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        WebView webView = findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        // 页面缩放
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        NewsItem newsItem = MainActivity.getIntentNewsItem(getIntent());
        String href = newsItem.getHref();
        String title = newsItem.getTitle();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        webView.loadUrl(href);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view,url);
            }
        });
    }
}
