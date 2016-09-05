package com.lamantin.sildingpanelayoutdemo.presenters;


import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;
import com.lamantin.sildingpanelayoutdemo.views.DetailsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class DetailsPresenter extends BasePresenter {

    private DetailsView view;

    Subscription subscription;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        //TODO
        loadData();
    }

    private void loadData() {
        subscription = sessionData.getAlbumsByUser(1).subscribe(albums -> {
            view.setAlbums(albums);
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

    public void setView(DetailsView view) {
        this.view = view;
    }

    public void onPhotoClick(Photo photo) {
        //TODO
    }
}
