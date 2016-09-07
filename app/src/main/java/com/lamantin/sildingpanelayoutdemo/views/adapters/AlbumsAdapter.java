package com.lamantin.sildingpanelayoutdemo.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends FragmentStatePagerAdapter {

    private List<Album> albums = new ArrayList<>();

    public AlbumsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return AlbumsView.create(albums.get(position));
    }

    @Override
    public int getCount() {
        return albums.size();
    }
}
