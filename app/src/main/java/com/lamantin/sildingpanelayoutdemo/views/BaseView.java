package com.lamantin.sildingpanelayoutdemo.views;


import com.lamantin.sildingpanelayoutdemo.presenters.BasePresenter;

import butterknife.Unbinder;

public interface BaseView {
    void showProgress();
    void hideProgress();
    void showEmptyList();

    BasePresenter getPresenter();
    Unbinder getUnbinder();
}
