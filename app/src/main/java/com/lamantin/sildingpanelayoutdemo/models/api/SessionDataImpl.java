package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.App;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SessionDataImpl implements SessionData {

    @Inject
    Api api;

    public SessionDataImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Album>> getAlbumsByUser(int userId) {
        return api.getAlbumsByUser(userId)
                .map(albumDTOs -> {
                    if (albumDTOs == null) {
                        return null;
                    }
                    return Observable.from(albumDTOs)
                            .map(Album::new)
                            .toList()
                            .toBlocking()
                            .first();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    @Override
    public Observable<List<Photo>> getPhotosByAlbum(int albumId) {
        return api.getPhotosByAlbum(albumId)
                .map(photosDTOs -> {
                    if (photosDTOs == null) {
                        return null;
                    }
                    return Observable.from(photosDTOs)
                            .map(Photo::new)
                            .toList()
                            .toBlocking()
                            .first();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    @Override
    public Observable<List<Photo>> getPhotoHistory() {
        return api.getPhotoHistory()
                .map(photosDTOs -> {
                    if (photosDTOs == null) {
                        return null;
                    }
                    return Observable.from(photosDTOs)
                            .map(Photo::new)
                            .toList()
                            .toBlocking()
                            .first();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }


}
