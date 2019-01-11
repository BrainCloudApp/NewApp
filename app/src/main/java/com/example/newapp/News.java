package com.example.newapp;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private String img;
    private String newsContent = "";

    public News(String newsName, String newsImageId){
        setTitle(newsImageId);
        setImg(newsName);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }



}
