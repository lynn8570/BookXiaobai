package com.lynn.bookxiaobai.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.util.VectorDrawableUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<BookBean> mFeedList;
    private Context mContext;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<BookBean> feedlist) {
        this.mFeedList = feedlist;
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
        if (bookBean.getUserNote().getState() == BookBean.UserNote.STATE_UNSTART) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, R.color.colorTimeline));
            holder.mState.setText(R.string.unstart);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextGrey));
        } else if (bookBean.getUserNote().getState() == BookBean.UserNote.STATE_READED) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorTimeline));
            holder.mState.setText(R.string.read);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextGrean));
        } else {
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorTimeline));
            holder.mState.setText(R.string.unread);
            holder.mState.setTextColor(mContext.getResources().getColor(R.color.colorTextPink));
        }

        holder.mTitle.setText(bookBean.getTitle());
        holder.mAuthor.setText(bookBean.getAuthor().get(0));

        holder.mTime.setText(TimeUtil.parseDateTime(bookBean.getUserNote().getNoteTime(), "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy"));


    }

    @Override
    public int getItemCount() {
        return mFeedList != null ? mFeedList.size() : 0;
    }
}
