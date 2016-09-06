package com.lamantin.sildingpanelayoutdemo.models.api;


import java.util.List;

import rx.Observable;

public interface SessionData {
    Observable<List<Album>> getAlbumsByUser(int userId);
    Observable<List<Photo>> getPhotosByAlbum(int albumId);
    Observable<List<Photo>> getPhotoHistory();
}
