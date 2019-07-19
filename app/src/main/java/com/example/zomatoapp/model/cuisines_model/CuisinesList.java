package com.example.zomatoapp.model.cuisines_model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisinesList {
    @SerializedName("cuisine")
    @Expose
    private List<Cuisines> cuisine = null;

    public List<Cuisines> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<Cuisines> cuisine) {
        this.cuisine = cuisine;
    }

}

