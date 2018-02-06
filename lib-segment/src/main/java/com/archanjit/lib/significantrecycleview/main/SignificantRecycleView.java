package com.archanjit.lib.significantrecycleview.main;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by satyam on 04/01/2018.
 */

public class SignificantRecycleView extends RelativeLayout {
    View customView;
    //RecyclerView recyclerView;
    EDRecyclerView recyclerView;
    TextView emptyTextView;
    Handler internalHandler = new Handler();

    private void printLog(String message) {
        Log.d(SignificantRecycleView.class.getName(), message + "");
    }

    public SignificantRecycleView(Context context) {
        super(context);
        init(context);
    }

    public SignificantRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SignificantRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SignificantRecycleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        customView = inflate(context, R.layout.advance_recycle_view, this);
        //recyclerView = (RecyclerView) customView.findViewById(R.id.recycleView);
        recyclerView = (EDRecyclerView) customView.findViewById(R.id.recycleView);
        emptyTextView = (TextView) customView.findViewById(R.id.emptyTextView);
    }

    private void updateEmptyView() {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            if (recyclerView.getAdapter().getItemCount() > 0) {
                emptyTextView.setVisibility(GONE);
            } else emptyTextView.setVisibility(VISIBLE);
        }
    }

    public RecyclerView.LayoutManager getLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    public RecyclerView.LayoutManager getStaggeredGridLayoutManage(int spanCount, int orientation) {
        return new StaggeredGridLayoutManager(spanCount, orientation);
    }

    public RecyclerView.LayoutManager getGridLayoutManager(Context context, int spanCount) {
        return new GridLayoutManager(context, spanCount);
    }

    public RecyclerView.LayoutManager getGridLayoutManager(Context context, int spanCount, @RecyclerView.Orientation int orientation, boolean reverseLayout) {
        return new GridLayoutManager(context, spanCount, orientation, reverseLayout);
    }

    public void setEnableScroll(boolean scroll) {
        recyclerView.setEnable(scroll);
    }

    public boolean isEnableScroll() {
        return recyclerView.isEnableScroll();
    }

    private void updateEmptyView(int itemCount) {
        if (itemCount > 0) {
            emptyTextView.setVisibility(GONE);
        } else emptyTextView.setVisibility(VISIBLE);
    }

    public void setLayoutManagerToView(RecyclerView.LayoutManager layout) {
        if (layout == null) {
            return;
        }
        recyclerView.setLayoutManager(layout);
    }

    public void setLinearLayoutManagerToView(Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
    public void setStaggeredGridLayoutManageToView(int spanCount, int orientation) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount,orientation));
    }
    public void setGridLayoutManagerToView(Context context, int spanCount) {
        recyclerView.setLayoutManager(new GridLayoutManager(context,spanCount));
    }

    public void setAdapterToView(RecyclerView.Adapter adapter) {
        if (adapter == null) {
            printLog("Adpater Is null");
            return;

        }
        recyclerView.setAdapter(adapter);
        updateEmptyView(adapter.getItemCount());
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                printLog("Onchange Called");
                updateEmptyView();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                updateEmptyView(itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                updateEmptyView(itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                updateEmptyView(itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                updateEmptyView(itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                updateEmptyView(itemCount);
            }
        });


    }

    /* For setting Empty Text */
    public void setEmptyTextMessage(String message) {
        emptyTextView.setText(message);
    }

    /* get Text View for customising it further*/
    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    /* To get Recycleview*/
    public RecyclerView getRecycleView() {
        return recyclerView;
    }



}
