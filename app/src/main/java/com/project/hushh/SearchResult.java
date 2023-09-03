package com.project.hushh;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("result_title")
    private String title;

    @SerializedName("result_link")
    private String link;

    // Getters and setters for title and link
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
