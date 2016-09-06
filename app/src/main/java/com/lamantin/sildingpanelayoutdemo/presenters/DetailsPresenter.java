package com.lamantin.sildingpanelayoutdemo.presenters;


import android.os.Bundle;
import android.util.Log;

import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.views.DetailsView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

public class DetailsPresenter extends BasePresenter {

    private static final String TAG = "DetailsPresenter";

    private DetailsView view;

    private Subscription subscriptionAlbums;

    private Subscription subscriptionHistory;
    LinkedList<Photo> photoLinkedList = new LinkedList<>();

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        //TODO data persistence
        loadData();
    }

    private void loadData() {
        view.showProgress();
        subscriptionAlbums = sessionData.getAlbumsByUser(1).subscribe(albums -> {
            view.setAlbums(albums);
        });
        subscriptionHistory = sessionData.getPhotoHistory().subscribe(photos -> {
            photoLinkedList.addAll(photos);
            view.setHistory(new LinkedList<>(photoLinkedList));
            view.hideProgress();
        });
    }

    @Override
    public void onDestroyView() {
        if(!subscriptionAlbums.isUnsubscribed()) {
            subscriptionAlbums.unsubscribe();
        }
        if(!subscriptionHistory.isUnsubscribed()) {
            subscriptionHistory.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //TODO
    }

    public void setView(DetailsView view) {
        this.view = view;
    }

    public void onPhotoClick(Photo photo) {
        Log.d(TAG, "onPhotoClick " + photo.getUrl());
        photoLinkedList.addFirst(photo);
        view.addToHistory(photo);
    }
}
