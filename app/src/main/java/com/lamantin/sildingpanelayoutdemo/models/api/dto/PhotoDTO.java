package com.lamantin.sildingpanelayoutdemo.models.api.dto;


import com.google.gson.annotations.Expose;

public class PhotoDTO {
    @Expose
    public int id;
    @Expose
    public int albumId;
    @Expose
    public String title;
    @Expose
    public String url;
    @Expose
    public String thumbnailUrl;

    public String toString() {
        return "id=" + id + " albumId=" + albumId + " title=" + title +
                " url=" + url + " thumbnailUrl=" + thumbnailUrl;
    }
}
