package com.example.zomatoapp.model.cuisines;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuisine {

    @SerializedName("cuisine")
    @Expose
    private CuisineData cuisine;

    public CuisineData getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineData cuisine) {
        this.cuisine = cuisine;
    }

}