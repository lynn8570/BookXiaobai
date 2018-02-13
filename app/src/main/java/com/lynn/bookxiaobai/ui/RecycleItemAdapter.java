package com.lynn.bookxiaobai.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.boxstore.base.BoxListAdapter;

import java.util.List;

/**
 * Created by lynn on 2018/2/12.
 */

public abstract class RecycleItemAdapter<T> extends RecyclerView.Adapter<TimeLineViewHolder> implements BoxListAdapter<T> {

    protected List<T> mFeedlist;


    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected ItemClickListener itemClickListener;


    public RecycleItemAdapter(List<T> feadlist){
        mFeedlist = feadlist;
    }
    public List<T> getmFeedlist() {
        return mFeedlist;
    }

    public void setItemClick(ItemClickListener listener) {
        itemClickListener = listener;
    }


    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, final int position) {
        if (itemClickListener != null) {
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view, position);
                }
            });

            holder.mState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onStateClick(view,position);
                }
            });
        }
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view;
        view = mLayoutInflater.inflate(R.layout.item_timeline, parent, false);

        return new TimeLineViewHolder(view, viewType);
    }


    @Override
    public int getItemCount() {
        return mFeedlist != null ? mFeedlist.size() : 0;
    }


    @Override
    public void updateList(List<T> datalist) {

        if (datalist == null) {
            throw new NullPointerException("TimeLineAdapter updatelist is null");
        }
        this.mFeedlist = datalist;
        notifyDataSetChanged();
    }
}
