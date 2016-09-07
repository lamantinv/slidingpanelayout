package com.lamantin.sildingpanelayoutdemo.models.api.dto;


import com.google.gson.annotations.Expose;

public class AlbumDTO {
    @Expose
    public long id;
    @Expose
    public int userId;
    @Expose
    public String title;

    public String toString() {
        return "id=" + id + " userId=" + userId + " title=" + title;
    }
}
