package com.lamantin.sildingpanelayoutdemo.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.R;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;

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
    AlbumsPresenter presenter;
    private Unbinder unbinder;

    @BindView(R.id.page_number)
    TextView pageNumber;

    @BindView(R.id.album_name)
    TextView albumName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        presenter.setAlbum((Album) getArguments().getSerializable(ALBUM));
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    public void setPageNumber(int number) {
        pageNumber.setText(String.valueOf(number));
    }

    public void setAlbumName(String name) {
        albumName.setText(name);
    }

    public void setPhotos(List<Photo> photos) {
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
