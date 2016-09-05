package com.lamantin.sildingpanelayoutdemo.models.api.dto;


import com.google.gson.annotations.Expose;

public class AlbumDTO {
    @Expose
    private int id;
    @Expose
    private int userId;
    @Expose
    private String title;

    public String toString() {
        return "id=" + id + " userId=" + userId + " title=" + title;
    }
}
