package com.example.zomatoapp.model.foryou;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForYouApiResponse {

    @SerializedName("establishments")
    @Expose
    private List<Establishment> establishments = null;

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

}