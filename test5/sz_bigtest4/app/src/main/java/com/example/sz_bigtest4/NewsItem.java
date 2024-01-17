package com.example.sz_bigtest4;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private String title;
    private String time;
    private String href;
    private String imgSrc;

    public NewsItem(String title, String time, String href, String imgSrc) {
        this.title = title;
        this.time = time;
        this.href = href;
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
