package com.lamantin.sildingpanelayoutdemo.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentView extends Fragment implements BaseView {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getPresenter() != null) {
            getPresenter().onCreateView(savedInstanceState);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(getPresenter() != null) {
            getPresenter().onDestroyView();
        }
        if(getUnbinder() != null) {
            getUnbinder().unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getPresenter() != null) {
            getPresenter().onSaveInstanceState(outState);
        }
    }
}
