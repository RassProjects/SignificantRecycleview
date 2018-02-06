package com.archanjit.lib.significantrecycleview.main;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by satyam on 11/01/2018.
 */

public abstract class SignificantAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;


    private boolean printLogs = true;

    public void setPrintLogs(boolean logStatus) {
        printLogs = logStatus;
    }

    public void printLog(String message) {
        if (getTag() != null && printLogs)
            Log.d(getTag(), message + "");
    }

    @IdRes
    public abstract int getRootViewId();

    public abstract String getTag();

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        if (holder != null) {
            View rootView = holder.itemView.findViewById(getRootViewId());
            if (rootView == null)
                throw new IllegalStateException("View is not available in layout.Check view id provided");

            if (onItemClickListener != null) {
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(view, position);
                    }
                });
            }

            if (onItemLongClickListener != null) {
                rootView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return onItemLongClickListener.onItemLongClick(view, position);
                    }
                });
            }
        }
    }


    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract int getItemCount();


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }



}
