package com.archanjit.lib.significantrecycleview.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by satyam on 11/01/2018.
 */

public class EDRecyclerView extends RecyclerView {

    private boolean mScrollingEnabled = true;

    public EDRecyclerView(Context context) {
        super(context);
    }

    public EDRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EDRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return (!mScrollingEnabled) || super.dispatchTouchEvent(ev);
    }

    public boolean isEnableScroll() {
        return mScrollingEnabled;
    }

    public void setEnable(boolean enable) {
        this.mScrollingEnabled = enable;
    }


}
