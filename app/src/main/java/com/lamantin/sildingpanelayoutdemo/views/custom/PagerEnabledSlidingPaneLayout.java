package com.lamantin.sildingpanelayoutdemo.views.custom;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class PagerEnabledSlidingPaneLayout extends SlidingPaneLayout {

    private final int mEdgeSlop;
    private float mInitialMotionX;

    public PagerEnabledSlidingPaneLayout(Context context) {
        this(context, null);
    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = ev.getX();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();

                if (mInitialMotionX > mEdgeSlop && !isOpen() && canScroll(this, false,
                        Math.round(x - mInitialMotionX), Math.round(x), Math.round(y))) {

                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(cancelEvent);
                }
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                final View child = group.getChildAt(i);
                if(child instanceof ViewPager) {
                    int left = child.getLeft();
                    int right = left + child.getWidth();
                    int top = child.getTop();
                    int bottom = top + child.getHeight();
                    if(x >= left && x < right &&
                            y >= top && y < bottom && ((ViewPager)child).canScrollHorizontally(-dx))
                    {
                        return true;
                    }
                } else {
                    if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() &&
                            y + scrollY >= child.getTop() && y + scrollY < child.getBottom() &&
                            canScroll(child, true, dx, x + scrollX - child.getLeft(),
                                    y + scrollY - child.getTop())) {
                        return true;
                    }
                }

            }
        }

        return checkV && ViewCompat.canScrollHorizontally(v, -dx);
    }
}