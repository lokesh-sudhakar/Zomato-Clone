package com.example.zomatoapp.model.cuisines_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuisines {

    @SerializedName("cuisine_id")
    @Expose
    private Integer cuisineId;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
