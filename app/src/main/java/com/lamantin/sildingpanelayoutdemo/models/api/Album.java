package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.models.api.dto.AlbumDTO;

public class Album {

    private int id;

    private String title;

    public Album(AlbumDTO albumDTO) {
        id = albumDTO.id;
        title = albumDTO.title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
