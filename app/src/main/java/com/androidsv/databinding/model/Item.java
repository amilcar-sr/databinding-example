package com.androidsv.databinding.model;

/**
 * Created by amilcar on 4/4/17.
 */

public class Item {

    private String id;

    private String title;

    private String subTitle;

    private String description;

    private String imageUrl;

    public Item() {
    }

    public Item(String title, String subTitle, String description, String imageUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
