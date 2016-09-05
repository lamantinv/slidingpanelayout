package com.lamantin.sildingpanelayoutdemo.views;


public interface BaseView {
    void showProgress();
    void hideProgress();
    void showError(String errorMessage);
}
