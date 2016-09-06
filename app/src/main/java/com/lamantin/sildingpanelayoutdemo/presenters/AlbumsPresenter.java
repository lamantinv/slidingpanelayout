package com.lamantin.sildingpanelayoutdemo.presenters;

import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;

import javax.inject.Inject;

import rx.Subscription;

public class AlbumsPresenter extends BasePresenter {

    private AlbumsView view;
    private Album album;

    Subscription subscription;

    @Inject
    SessionData sessionData;

    @Inject
    DetailsPresenter detailsPresenter;

    public AlbumsPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        view.setAlbumName(album.getTitle());
        view.setPageNumber(album.getId());
        loadData();
    }

    public void loadData() {
        view.showProgress();
        subscription = sessionData.getPhotosByAlbum(album.getId()).subscribe(photos -> {
            view.setPhotos(photos);
            view.hideProgress();
        });
    }

    @Override
    public void onDestroyView() {
        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //TODO
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
