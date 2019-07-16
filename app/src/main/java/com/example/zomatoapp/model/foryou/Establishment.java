package com.example.zomatoapp.model.foryou;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Establishment {

    @SerializedName("establishment")
    @Expose
    private Establishment_ establishment;

    public Establishment_ getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment_ establishment) {
        this.establishment = establishment;
    }

}