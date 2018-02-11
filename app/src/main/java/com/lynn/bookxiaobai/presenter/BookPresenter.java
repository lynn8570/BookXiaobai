package com.lynn.bookxiaobai.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.lynn.bookxiaobai.App;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.manager.DataManager;
import com.lynn.bookxiaobai.ui.TimeLineAdapter;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.view.BookView;
import com.lynn.bookxiaobai.view.View;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscriptionList;
import io.objectbox.android.AndroidScheduler;


import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class BookPresenter implements Presenter {

    private DataManager manager;

    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;

    private TimeLineAdapter mTimeLineAdapter;
    private BookBean mBooBean;



    public BookPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();



    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {//activity ͨ�� P ���� onStop ����һЩ��������
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mBookView = (BookView) view;  //���ں���ͨ��mBookView�ڻ�ȡ������֮�����һЩ�ص���������������ǣ�����ʧ�ܣ�����״̬�仯��
    }



    @Override
    public void attatchIncomingIntent(Intent intent) {

    }



    public void getBookByIsbn(String strIsbn) {
        mCompositeSubscription.add(manager.getBookByIsbn(strIsbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        mBookView.onSuccess(mBooBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("linlian", "e=" + e);
                        mBookView.onError("get book by isbn error");
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        mBooBean = bookBean;
                    }
                }));
    }

    public void getSearchBooks(String name, String author, int start, int count) {

    }

    public void getBookById(String strId) {
        mCompositeSubscription.add(manager.getBookById(strId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        mBookView.onSuccess(mBooBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mBookView.onError("get book by id error");
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        mBooBean = bookBean;
                    }
                }));
    }
}
