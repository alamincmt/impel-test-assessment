package com.alamincmt.news.adapters;

import android.net.Uri;

public class NewsModel {
    public int getImgUri() {
        return imgUri;
    }

    public void setImgUri(int imgUri) {
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    int imgUri;
    String title;
    String subTitle;

    public NewsModel(int imgUri, String title, String subTitle) {
        this.imgUri = imgUri;
        this.title = title;
        this.subTitle = subTitle;
    }

}
