package com.lamantin.sildingpanelayoutdemo.di;

import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    AlbumsPresenter provideAlbumsPresenter() {
        return new AlbumsPresenter();
    }
}
