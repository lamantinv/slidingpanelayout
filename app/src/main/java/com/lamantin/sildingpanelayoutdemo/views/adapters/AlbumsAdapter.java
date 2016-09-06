package com.lamantin.sildingpanelayoutdemo.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "AlbumsAdapter";
    private List<Album> albums = new ArrayList<>();
    private DetailsPresenter detailsPresenter;

    public AlbumsAdapter(FragmentManager fragmentManager, DetailsPresenter detailsPresenter) {
        super(fragmentManager);
        this.detailsPresenter = detailsPresenter;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "create new item position : " + position);
        return AlbumsView.create(albums.get(position), detailsPresenter);
    }

    @Override
    public int getCount() {
        return albums.size();
    }
}
