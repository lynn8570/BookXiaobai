package com.lynn.bookxiaobai.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.boxstore.base.BoxListAdapter;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.util.VectorDrawableUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> implements BoxListAdapter<BookBeanMini> {

    private List<BookBeanMini> mFeedList;
    private Context mContext;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<BookBeanMini> feedlist) {
        this.mFeedList = feedlist;
    }

    public void updateList(List<BookBeanMini> list){
        if(list==null){
            throw new NullPointerException("TimeLineAdapter updatelist is null");
        }
        this.mFeedList=list;
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

        BookBeanMini bookBeanMini = mFeedList.get(position);
        if (bookBeanMini.getState() == BookBeanMini.STATE_UNSTART) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, R.color.colorTimeline));
            holder.mState.setText(R.string.unstart);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextGrey));
        } else if (bookBeanMini.getState() == BookBeanMini.STATE_READED) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorTimeline));
            holder.mState.setText(R.string.read);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextGrean));
        } else {
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorTimeline));
            holder.mState.setText(R.string.unread);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextPink));
        }

        holder.mTitle.setText(bookBeanMini.getTitile());
        holder.mAuthor.setText(bookBeanMini.getAuthor());

        holder.mTime.setText(TimeUtil.parseDateTimeSpan(bookBeanMini.getNoteTime()));


    }

    @Override
    public int getItemCount() {
        return mFeedList != null ? mFeedList.size() : 0;
    }
}
