package com.lamantin.sildingpanelayoutdemo.di;


import com.lamantin.sildingpanelayoutdemo.models.api.Api;
import com.lamantin.sildingpanelayoutdemo.models.api.ApiProvider;
import com.lamantin.sildingpanelayoutdemo.other.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    @Singleton
    Api provideApi() {
        return ApiProvider.getApi(Constants.BASE_URL);
    }
}
