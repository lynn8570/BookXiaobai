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
import com.lynn.bookxiaobai.ui.ItemClickListener;
import com.lynn.bookxiaobai.ui.TimeLineViewHolder;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.util.VectorDrawableUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class BookItemAdapter extends RecycleItemAdapter<BookBean> {




    public BookItemAdapter(List<BookBean> feedlist) {
        super(feedlist);
        this.mFeedlist = feedlist;
    }




    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, final int position) {

        super.onBindViewHolder(holder,position);
        BookBean bookBean = mFeedlist.get(position);


        holder.mTitle.setText(bookBean.getTitle());
        holder.mAuthor.setText(bookBean.getAuthor().get(0));

        holder.mTime.setText("");



    }

}
