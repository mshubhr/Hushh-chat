package com.project.hushh;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {

    @SerializedName("results")
    private List<SearchResult> items;

    public SearchResponse(List<SearchResult> items) {
        this.items = items;
    }

    // Getter and setter for items
    public List<SearchResult> getItems() {
        return items;
    }
    public void setItems(List<SearchResult> items) {
        this.items = items;
    }

}
