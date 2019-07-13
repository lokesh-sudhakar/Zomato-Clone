package com.example.zomatoapp.model.collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collections {

    @SerializedName("collection")
    @Expose
    private Collection restaurantCollections;

    public Collection getCollection() {
        return restaurantCollections;
    }

    public void setCollection(Collection collection) {
        this.restaurantCollections = collection;
    }

}