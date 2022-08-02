package com.anf.core.dataobjects;

public class NewsFeedVO {

    private String title;
    private String description;
    private String author;
    private String content;
    private String ctaUrl;
    private String image;
    private String createdDate;

    @Override
    public String toString() {
        return "NewsFeedVO [author=" + author + ", content=" + content + ", createdDate=" + createdDate + ", ctaUrl="
                + ctaUrl + ", description=" + description + ", image=" + image + ", title=" + title + "]";
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtaUrl() {
        return ctaUrl;
    }

    public void setCtaUrl(String ctaUrl) {
        this.ctaUrl = ctaUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
