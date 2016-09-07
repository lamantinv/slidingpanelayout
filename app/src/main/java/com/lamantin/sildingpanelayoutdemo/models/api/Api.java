package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.models.api.dto.AlbumDTO;
import com.lamantin.sildingpanelayoutdemo.models.api.dto.PhotoDTO;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface Api {
    @GET("/users/{user}/albums")
    Observable<List<AlbumDTO>> getAlbumsByUser(@Path("user") int user);
    @GET("/albums/{album}/photos")
    Observable<List<PhotoDTO>> getPhotosByAlbum(@Path("album") long album);
    @GET("/albums/11/photos")
    Observable<List<PhotoDTO>> getPhotoHistory();
}