package com.lamantin.sildingpanelayoutdemo.presenters;

import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class AlbumsPresenter extends BasePresenter {

    private static final String NEED_TO_RECREATE = "needToRecreate";

    private AlbumsView view;
    private Album album;

    Subscription subscription;

    @Inject
    SessionData sessionData;

    @Inject
    DetailsPresenter detailsPresenter;

    private Action1<List<Photo>> photoCallback = photos -> {
        view.setPhotos(photos);
        view.hideProgress();
    };

    public AlbumsPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        view.setAlbumName(album.title());
        view.setPageNumber(album.id());
        loadData(savedInstanceState != null && savedInstanceState.getBoolean(NEED_TO_RECREATE, false));
    }

    public void loadData(boolean recreated) {
        view.showProgress();
        if(recreated) {
            subscription = sessionData.getPhotoByAlbumDB(album.id()).subscribe(photoCallback);
        } else {
            subscription = sessionData.getPhotosByAlbum(album.id()).subscribe(photoCallback);
        }

    }

    @Override
    public void onDestroyView() {
        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(NEED_TO_RECREATE, true);
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setView(AlbumsView view) {
        this.view = view;
    }

    public void onPhotoClick(Photo photo) {
        detailsPresenter.onPhotoClick(photo);
    }
}
