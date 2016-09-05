package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.models.api.dto.PhotoDTO;

public class Photo {

    private String title;

    private String url;

    public Photo(PhotoDTO photoDTO) {
        title = photoDTO.title;
        url = photoDTO.url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
