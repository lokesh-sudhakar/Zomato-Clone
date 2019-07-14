package com.example.zomatoapp.model.collection;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionsApiResponse {

    @SerializedName("collections")
    @Expose
    private List<Collections> collections = null;
    @SerializedName("has_more")
    @Expose
    private Integer hasMore;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("display_text")
    @Expose
    private String displayText;
    @SerializedName("has_total")
    @Expose
    private Integer hasTotal;

    public List<Collections> getCollections() {
        return collections;
    }

    public void setCollections(List<Collections> collections) {
        this.collections = collections;
    }

    public Integer getHasMore() {
        return hasMore;
    }

    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Integer getHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(Integer hasTotal) {
        this.hasTotal = hasTotal;
    }

}