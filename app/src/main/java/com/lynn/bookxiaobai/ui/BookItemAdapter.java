package com.lynn.bookxiaobai.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.boxstore.base.BoxListAdapter;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.util.VectorDrawableUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class BookItemAdapter extends RecyclerView.Adapter<TimeLineViewHolder> implements BoxListAdapter<BookBean> {

    private List<BookBean> mFeedList;
    private Context mContext;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;

    public BookItemAdapter(List<BookBean> feedlist) {
        this.mFeedList = feedlist;
    }

    public void updateList(List<BookBean> list) {
        if (list == null) {
            throw new NullPointerException("TimeLineAdapter updatelist is null");
        }
        this.mFeedList = list;
        notifyDataSetChanged();
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
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        BookBean bookBean = mFeedList.get(position);


        holder.mTitle.setText(bookBean.getTitle());
        holder.mAuthor.setText(bookBean.getAuthor().get(0));

        holder.mTime.setText("");


    }

    @Override
    public int getItemCount() {
        return mFeedList != null ? mFeedList.size() : 0;
    }
}
