package com.lamantin.sildingpanelayoutdemo.models.api;


import android.content.ContentValues;

import com.lamantin.sildingpanelayoutdemo.App;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SessionDataImpl implements SessionData {

    private static final String TAG = "SessionDataImpl";
    @Inject
    Api api;

    @Inject
    BriteDatabase db;

    public SessionDataImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Album>> getAlbumsByUser(int userId) {
        return Observable.create((Observable.OnSubscribe<List<Album>>) subscriber -> {
            getAlbumsDB().subscribe(subscriber::onNext);
            api.getAlbumsByUser(userId)
                    .map(albumsDTOs -> {
                        if (albumsDTOs == null) {
                            subscriber.onError(new NullPointerException());
                            return null;
                        }
                        return Observable.from(albumsDTOs)
                                .map(Album::fromDto)
                                .toList()
                                .toBlocking()
                                .first();
                    }).onErrorResumeNext(throwable -> {
                        throwable.printStackTrace();
                        return Observable.empty();
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(albums -> {
                        subscriber.onNext(albums);
                        saveAlbums(albums);
                    });
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    @Override
    public Observable<List<Photo>> getPhotosByAlbum(long albumId) {
        return Observable.create((Observable.OnSubscribe<List<Photo>>) subscriber -> {
            getPhotoByAlbumDB(albumId).subscribe(photosFromDB -> {
                if(photosFromDB.isEmpty()) {
                    api.getPhotosByAlbum(albumId)
                            .map(photosDTOs -> {
                                if (photosDTOs == null) {
                                    subscriber.onNext(null);
                                    return null;
                                }
                                return Observable.from(photosDTOs)
                                        .map(Photo::fromDTO)
                                        .toList()
                                        .toBlocking()
                                        .first();
                            })
                            .onErrorResumeNext(throwable -> {
                                throwable.printStackTrace();
                                return Observable.empty();
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe(photos -> {
                                subscriber.onNext(photos);
                                savePhotos(albumId, photos);
                            });
                } else {
                    subscriber.onNext(photosFromDB);
                }
            });

        })
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(throwable -> {
                throwable.printStackTrace();
                return Observable.empty();
            });
    }

    @Override
    public Observable<List<Photo>> getPhotoHistory() {
        QueryObservable query = db.createQuery(Photo.HISTORY_TABLE, Photo.QUERY_PHOTO_FROM__HISTORY);
        return query.mapToList(Photo.MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .first()
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    @Override
    public Observable<List<Photo>> getPhotoByAlbumDB(long albumId) {
        QueryObservable query = db.createQuery(Photo.TABLE, Photo.QUERY_PHOTO_BY_ALBUM, String.valueOf(albumId));
        return query.mapToList(Photo.MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .first()
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    private void saveAlbums(List<Album> albums) {
        BriteDatabase.Transaction transaction = db.newTransaction();
        for(Album album : albums) {
            saveAlbum(album);
        }
        transaction.markSuccessful();
        transaction.end();
    }

    private void savePhotos(long albumId, List<Photo> photos) {
        BriteDatabase.Transaction transaction = db.newTransaction();
        for(Photo photo : photos) {
            savePhoto(albumId, photo);
        }
        transaction.markSuccessful();
        transaction.end();
    }

    @Override
    public Observable<List<Album>> getAlbumsDB() {
        QueryObservable query = db.createQuery(Album.TABLE, Album.QUERY_ALL);
        return query.mapToList(Album.MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .first()
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return Observable.empty();
                });
    }

    @Override
    public void saveAlbum(Album album) {
        ContentValues cv = new Album.Builder()
                .id(album.id())
                .title(album.title())
                .build();
        int numberOfUpdated = db.update(Album.TABLE, cv, Album.ID + "=" + album.id());
        if(numberOfUpdated == 0) {
            db.insert(Album.TABLE, cv);
        }
    }

    @Override
    public void savePhoto(long albumId, Photo photo) {
        ContentValues cv = new Photo.Builder()
                .id(photo.id())
                .albumId(albumId)
                .thumbnail(photo.thumbnail())
                .title(photo.title())
                .url(photo.url())
                .thumbnail(photo.thumbnail()).build();
        int numberOfUpdated = db.update(Photo.TABLE, cv, Photo.ID + "=" + photo.id());
        if(numberOfUpdated == 0) {
            db.insert(Photo.TABLE, cv);
        }
    }

    @Override
    public void savePhotoToHistory(Photo photo) {
        ContentValues cv = new Photo.Builder()
                .id(photo.id())
                .albumId(1)
                .thumbnail(photo.thumbnail())
                .title(photo.title())
                .url(photo.url())
                .thumbnail(photo.thumbnail()).build();

        db.insert(Photo.HISTORY_TABLE, cv);
    }

}
