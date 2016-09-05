package com.lamantin.sildingpanelayoutdemo.presenters;


import android.os.Bundle;

public abstract class BasePresenter {

    public abstract void onCreateView(Bundle savedInstanceState);

    public abstract void onDestroyView();

    public abstract void onSaveInstanceState(Bundle savedInstanceState);

}
