package com.lamantin.sildingpanelayoutdemo.presenters;


import android.os.Bundle;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.models.api.SessionData;

import javax.inject.Inject;

public abstract class BasePresenter {

    @Inject
    protected SessionData sessionData;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    public abstract void onCreateView(Bundle savedInstanceState);

    public abstract void onDestroyView();

    public abstract void onSaveInstanceState(Bundle savedInstanceState);

}
