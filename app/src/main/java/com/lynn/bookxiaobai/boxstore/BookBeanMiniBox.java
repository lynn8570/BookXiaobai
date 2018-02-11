package com.lynn.bookxiaobai.boxstore;

import android.text.TextUtils;

import com.lynn.bookxiaobai.boxstore.base.BaseBoxManager;
import com.lynn.bookxiaobai.boxstore.base.BoxListAdapter;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.entity.BookBeanMini_;
import com.lynn.bookxiaobai.util.TimeUtil;

import java.util.List;

import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscriptionList;

/**
 * Created by lynn on 2018/2/10.
 */

public class BookBeanMiniBox  extends BaseBoxManager<BookBeanMini>{

    public BookBeanMiniBox(Class<BookBeanMini> entityClazz) {
        super(entityClazz);
    }

    @Override
    public String getTableName() {
        return BookBeanMini_.__DB_NAME;
    }

    public BookBeanMini query(String id){
         return getQueryBuilder().equal(BookBeanMini_.id,id).build().findFirst();
    }


    public void bindBoxListAdapter(final BoxListAdapter<BookBeanMini> adapter,DataSubscriptionList subscriptionList){
        getQueryBuilder().order(BookBeanMini_.idbox).build()
                .subscribe(subscriptionList)
                .on(AndroidScheduler.mainThread())
                .observer(new DataObserver<List<BookBeanMini>>() {
                    @Override
                    public void onData(List<BookBeanMini> data) {
                        adapter.updateList(data);
                    }
                });
    }

    public long insert(BookBean bookBean){
        if (bookBean==null)
            return -1;

        if(TextUtils.isEmpty(bookBean.getId())){
            return -1;
        }

        BookBeanMini query = query(bookBean.getId());
        if(query!=null){
            //already exist
            return -1;
        }
        BookBeanMini bookBeanMini = null;
        if (bookBean != null) {
            bookBeanMini = bookBean.extraBookBeanMini();

        }
        bookBeanMini.setState(BookBeanMini.STATE_UNREADED);
        bookBeanMini.setStateTime(TimeUtil.getTime(null));
        return insert(bookBeanMini);
    }
}
