package com.lynn.bookxiaobai.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.lynn.bookxiaobai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TimeLineViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.item_title)
    TextView mTitle;
    @BindView(R.id.item_state)
    TextView mState;
    @BindView(R.id.item_author)
    TextView mAuthor;
    @BindView(R.id.item_time)
    TextView mTime;

    @BindView(R.id.time_marker)
    TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView,int viewType) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTimelineView.initLine(viewType);
    }
}
