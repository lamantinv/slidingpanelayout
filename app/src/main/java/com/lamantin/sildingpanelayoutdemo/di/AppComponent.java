package com.lamantin.sildingpanelayoutdemo.di;

import com.lamantin.sildingpanelayoutdemo.models.api.SessionDataImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(SessionDataImpl sessionData);
}
