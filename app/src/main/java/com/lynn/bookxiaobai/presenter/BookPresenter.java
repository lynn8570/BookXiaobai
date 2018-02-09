package com.lynn.bookxiaobai.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.manager.DataManager;
import com.lynn.bookxiaobai.view.BookView;
import com.lynn.bookxiaobai.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class BookPresenter implements Presenter {

    private DataManager manager;

    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private BookBean mBooBean;

    public BookPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {//activity ͨ�� P ���� onCreate ����һЩ��ʼ��
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
                            mBookView.onError("ͨ��ISBN�Ų�������ʧ��");
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
                        mBookView.onError("ͨ��ID�Ų�������ʧ��");
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        mBooBean = bookBean;
                    }
                }));
    }
}
