package com.alamincmt.news.adapters;

public class NewsModel {

    private String imgUri;
    private String title;
    private String description;
    private String sourceName;
    private String author;
    private String publishDate;
    private String authorID;

    public NewsModel(String imgUri, String title, String description, String sourceName, String author, String publishDate, String authorID) {
        this.imgUri = imgUri;
        this.title = title;
        this.description = description;
        this.sourceName = sourceName;
        this.author = author;
        this.publishDate = publishDate;
        this.authorID = authorID;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

}
