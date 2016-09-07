package com.lamantin.sildingpanelayoutdemo;


import android.app.Application;

import com.lamantin.sildingpanelayoutdemo.di.AppComponent;
import com.lamantin.sildingpanelayoutdemo.di.DaggerAppComponent;
import com.lamantin.sildingpanelayoutdemo.di.DbModule;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .dbModule(new DbModule(this))
                .build();
    }
}
