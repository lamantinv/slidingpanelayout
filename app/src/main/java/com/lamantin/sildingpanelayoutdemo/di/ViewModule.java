package com.lamantin.sildingpanelayoutdemo.di;

import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Singleton
    @Provides
    DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    AlbumsPresenter provideAlbumsPresenter() {
        return new AlbumsPresenter();
    }
}