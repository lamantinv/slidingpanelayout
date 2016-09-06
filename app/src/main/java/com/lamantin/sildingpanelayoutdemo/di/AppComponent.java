package com.lamantin.sildingpanelayoutdemo.di;

import com.lamantin.sildingpanelayoutdemo.models.api.SessionDataImpl;
import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;
import com.lamantin.sildingpanelayoutdemo.presenters.DetailsPresenter;
import com.lamantin.sildingpanelayoutdemo.views.AlbumsView;
import com.lamantin.sildingpanelayoutdemo.views.DetailsView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(SessionDataImpl sessionData);

    void inject(DetailsView detailsView);

    void inject(AlbumsView albumsView);

    void inject(DetailsPresenter basePresenter);

    void inject(AlbumsPresenter basePresenter);

}
