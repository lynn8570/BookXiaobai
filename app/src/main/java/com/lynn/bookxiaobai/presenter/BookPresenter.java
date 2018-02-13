package com.lynn.bookxiaobai.presenter;

import android.content.Context;
import android.util.Log;

import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.presenter.base.Presenter;
import com.lynn.bookxiaobai.ui.TimeLineAdapter;


import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BookPresenter extends Presenter.PresenterBase<BookBean> {


    private TimeLineAdapter mTimeLineAdapter;
    private BookBean mBooBean;


    public BookPresenter(Context context) {
        super(context);
        this.mContext = context;
    }


    public void getBookByIsbn(String strIsbn) {
        mCompositeSubscription.add(manager.getBookByIsbn(strIsbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        mView.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("linlian", "e=" + e);
                        mView.onError("get book by isbn error");
                    }

                    @Override
                    public void onNext(BookBean bookBean) {

                        mBooBean = bookBean;
                        mView.onSuccess(bookBean);
                    }
                }));
    }


    public void getBookById(String strId) {
        mCompositeSubscription.add(manager.getBookById(strId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        mView.onSuccess(mBooBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("get book by id error");
                    }

                    @Override
                    public void onNext(BookBean bookBean) {

                        mBooBean = bookBean;
                        mView.onSuccess(bookBean);
                    }
                }));
    }
}
