package com.lamantin.sildingpanelayoutdemo.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.R;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;
import com.lamantin.sildingpanelayoutdemo.views.adapters.PhotosGridAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlbumsView extends FragmentView {

    private static final String ALBUM = "album";
    public static AlbumsView create(Album album) {
        AlbumsView view = new AlbumsView();
        Bundle args = new Bundle();
        args.putSerializable(ALBUM, album);
        view.setArguments(args);
        return view;
    }

    @Inject
    AlbumsPresenter albumsPresenter;

    private Unbinder unbinder;
    @BindView(R.id.page_number)
    TextView pageNumber;

    @BindView(R.id.album_name)
    TextView albumName;

    @BindView(R.id.photos_recycler)
    RecyclerView photosRecycler;

    @BindView(R.id.photos_pb)
    ProgressBar photosProgressBar;

    private LinearLayoutManager layoutManager;

    PhotosGridAdapter photosGridAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        albumsPresenter.setAlbum((Album) getArguments().getSerializable(ALBUM));
        albumsPresenter.setView(this);
        photosGridAdapter = new PhotosGridAdapter(albumsPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        initPhotosRecycler();
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initPhotosRecycler() {
        photosRecycler.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        photosRecycler.setLayoutManager(layoutManager);
        photosRecycler.setAdapter(photosGridAdapter);
    }

    @Override
    public void showProgress() {
        photosProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        photosProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
    }

    public void setPageNumber(long number) {
        pageNumber.setText(String.valueOf(number));
    }

    public void setAlbumName(String name) {
        albumName.setText(name);
    }

    public void setPhotos(List<Photo> photos) {
        photosGridAdapter.setValues(photos);
    }

    @Override
    public BasePresenter getPresenter() {
        return albumsPresenter;
    }

    @Override
    public Unbinder getUnbinder() {
        return unbinder;
    }
}
