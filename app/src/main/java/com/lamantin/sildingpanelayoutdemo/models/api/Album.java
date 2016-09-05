package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.models.api.dto.AlbumDTO;

import java.io.Serializable;

public class Album implements Serializable {

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
