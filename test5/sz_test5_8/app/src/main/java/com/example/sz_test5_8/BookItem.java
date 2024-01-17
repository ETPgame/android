package com.example.sz_test5_8;

import java.io.Serializable;

public class BookItem implements Serializable {
    private String title;
    private String author;
    private String href;
    private String imgSrc;

    public BookItem(String title, String author, String href, String imgSrc) {
        this.title = title;
        this.author = author;
        this.href = href;
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return String.format("Title=%s\n" +
                "Author=%s\n" +
                "Href=%s\n" +
                "ImgSrc=%s\n",
                title,author,href,imgSrc);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getHref() {
        return href;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}

