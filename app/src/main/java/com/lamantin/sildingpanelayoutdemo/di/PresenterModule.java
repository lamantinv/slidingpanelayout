package com.lamantin.sildingpanelayoutdemo.di;

import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionDataImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    SessionData provideSessionData() {
        return new SessionDataImpl();
    }
}
