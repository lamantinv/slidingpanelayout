package com.lamantin.sildingpanelayoutdemo.presenters;

import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;

import rx.Subscription;

public class AlbumsPresenter extends BasePresenter {

    private AlbumsView view;
    private Album album;

    Subscription subscription;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        view.setAlbumName(album.getTitle());
        view.setPageNumber(album.getId());
        loadData();
    }

    public void loadData() {
        subscription = sessionData.getPhotosByAlbum(album.getId()).subscribe(photos -> {
           view.setPhotos(photos);
        });
    }

    @Override
    public void onDestroyView() {
        //TODO
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
//        detailsPresenter.onPhotoClick(photo);
    }
}
