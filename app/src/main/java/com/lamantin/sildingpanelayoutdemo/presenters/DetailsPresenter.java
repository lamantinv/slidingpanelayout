package com.lamantin.sildingpanelayoutdemo.presenters;


import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;
import com.lamantin.sildingpanelayoutdemo.views.DetailsView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class DetailsPresenter extends BasePresenter {

    private static final String TAG = "DetailsPresenter";
    private static final String NEED_TO_RECREATE = "needToRecreate";

    private DetailsView view;

    private Subscription subscriptionAlbums;

    private Subscription subscriptionHistory;
    LinkedList<Photo> photoLinkedList = new LinkedList<>();

    @Inject
    SessionData sessionData;
    private Action1<List<Album>> albumsCallback = albums -> {
        view.setAlbums(albums);
    };
    private Action1<List<Photo>> photoCallback = photos -> {
        photoLinkedList.addAll(photos);
        view.setHistory(new LinkedList<>(photoLinkedList));
        view.hideProgress();
    };

    public DetailsPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        loadData(savedInstanceState != null && savedInstanceState.getBoolean(NEED_TO_RECREATE, false));
    }

    private void loadData(boolean recreated) {
        view.showProgress();
        if(recreated) {
            subscriptionAlbums = sessionData.getAlbumsDB().subscribe(albumsCallback);
            subscriptionHistory = sessionData.getPhotoHistoryDB().subscribe(photoCallback);
        } else {
            subscriptionAlbums = sessionData.getAlbumsByUser(1).subscribe(albumsCallback);
            subscriptionHistory = sessionData.getPhotoHistory().subscribe(photoCallback);
        }
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
        savedInstanceState.putBoolean(NEED_TO_RECREATE, true);
    }

    public void setView(DetailsView view) {
        this.view = view;
    }

    public void onPhotoClick(Photo photo) {
        sessionData.savePhotoToHistory(photo);
        photoLinkedList.addFirst(photo);
        view.addToHistory(photo);
    }
}
