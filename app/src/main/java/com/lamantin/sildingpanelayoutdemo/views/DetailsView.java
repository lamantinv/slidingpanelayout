package com.lamantin.sildingpanelayoutdemo.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.R;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;
import com.lamantin.sildingpanelayoutdemo.views.adapters.AlbumsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsView extends FragmentView {

    @Inject
    DetailsPresenter presenter;
    private Unbinder unbinder;
    private AlbumsAdapter albumsAdapter;

    @BindView(R.id.albums_pager)
    ViewPager albumsViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        albumsAdapter = new AlbumsAdapter(getFragmentManager());
        albumsViewPager.setAdapter(albumsAdapter);
        super.onCreateView(inflater, container, savedInstanceState);
        presenter.setView(this);
        return view;
    }

    public void setHistory(List<Photo> photoList) {
        //TODO
    }

    public void addToHistory(Photo photo) {
        //TODO
    }

    public void setAlbums(List<Album> albums) {
        albumsAdapter.setAlbums(albums);
    }

    @Override
    public void showProgress() {
        //TODO
    }

    @Override
    public void hideProgress() {
        //TODO
    }

    @Override
    public void showError(String errorMessage) {
        //TODO
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public Unbinder getUnbinder() {
        return unbinder;
    }


}
