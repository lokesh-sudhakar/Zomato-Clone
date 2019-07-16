package com.example.zomatoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuisineData {

    @SerializedName("cuisine_id")
    @Expose
    private Integer cuisineId;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;

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


}
