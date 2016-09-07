package com.lamantin.sildingpanelayoutdemo.models.api;


import java.util.List;

import rx.Observable;

public interface SessionData {
    Observable<List<Album>> getAlbumsByUser(int userId);

    Observable<List<Photo>> getPhotosByAlbum(long albumId);

    Observable<List<Photo>> getPhotoHistory();

    Observable<List<Photo>> getPhotoHistoryDB();

    Observable<List<Photo>> getPhotoByAlbumDB(long albumId);

    Observable<List<Album>> getAlbumsDB();

    void saveAlbum(Album album);

    void savePhoto(long albumId, Photo photo);

    void savePhotoToHistory(Photo photo);
}
