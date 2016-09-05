package com.lamantin.sildingpanelayoutdemo.models.api.dto;


import com.google.gson.annotations.Expose;

public class PhotoDTO {
    @Expose
    private int id;
    @Expose
    private int albumId;
    @Expose
    private String title;
    @Expose
    private String url;
    @Expose
    private String thumbnailUrl;

    public String toString() {
        return "id=" + id + " albumId=" + albumId + " title=" + title +
                " url=" + url + " thumbnailUrl=" + thumbnailUrl;
    }
}
