package com.lamantin.sildingpanelayoutdemo.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.R;
import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;
import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;
import com.lamantin.sildingpanelayoutdemo.views.adapters.AlbumsAdapter;
import com.lamantin.sildingpanelayoutdemo.views.adapters.PhotosHistoryAdapter;
import com.lamantin.sildingpanelayoutdemo.views.custom.PagerEnabledSlidingPaneLayout;

import org.w3c.dom.Text;

import java.util.LinkedList;
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

    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;

    @BindView(R.id.history_pb)
    ProgressBar historyProgressBar;

    @BindView(R.id.error_field)
    TextView errorField;

    PhotosHistoryAdapter historyAdapter = new PhotosHistoryAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
        initHistoryRecycler();
        presenter.setView(this);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initHistoryRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        historyRecycler.setHasFixedSize(true);
        historyRecycler.setLayoutManager(linearLayoutManager);
        historyRecycler.setAdapter(historyAdapter);
    }

    private void initViewPager() {
        albumsAdapter = new AlbumsAdapter(getFragmentManager());
        albumsViewPager.setAdapter(albumsAdapter);
    }

    public void setHistory(LinkedList<Photo> photoList) {
        errorField.setVisibility(View.GONE);
        ViewCompat.setAlpha(historyRecycler, 0f);
        historyAdapter.setValues(photoList);
        ViewCompat.animate(historyRecycler).alpha(1f).setDuration(500).start();
        historyRecycler.scrollToPosition(historyAdapter.getItemCount() - 1);
    }

    public void addToHistory(Photo photo) {
        errorField.setVisibility(View.GONE);
        historyAdapter.addValue(photo);
        historyRecycler.scrollToPosition(historyAdapter.getItemCount() - 1);
    }

    public void setAlbums(List<Album> albums) {
        albumsAdapter.setAlbums(albums);
    }

    @Override
    public void showProgress() {
        historyProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        historyProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyList() {
        errorField.setVisibility(View.VISIBLE);
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
