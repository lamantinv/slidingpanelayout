package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.models.api.dto.PhotoDTO;

public class Photo {

    private final String title;

    private final String url;

    private String thumbnail;

    public Photo(PhotoDTO photoDTO) {
        title = photoDTO.title;
        url = photoDTO.url;
        thumbnail = photoDTO.thumbnailUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
