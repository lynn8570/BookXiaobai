package com.lynn.bookxiaobai.presenter.base;

import android.content.Context;
import android.content.Intent;

import com.lynn.bookxiaobai.manager.DataManager;
import com.lynn.bookxiaobai.view.BookView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by lynn on 2018/2/7.
 */


public interface Presenter<T> {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(BookView<T> view);

    void attatchIncomingIntent(Intent intent);

    /**
     * Created by lynn on 2018/2/11.
     */

    class PresenterBase<T> implements Presenter<T> {

        protected DataManager manager;
        protected CompositeSubscription mCompositeSubscription;

        protected Context mContext;

        protected BookView<T> mView;

        public PresenterBase(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            mCompositeSubscription = new CompositeSubscription();
            manager = new DataManager(mContext);
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

            if (mCompositeSubscription.hasSubscriptions()) {
                mCompositeSubscription.unsubscribe();
            }
        }

        @Override
        public void pause() {

        }

        @Override
        public void attachView(BookView<T> view) {

            mView = view;
        }


        @Override
        public void attatchIncomingIntent(Intent intent) {

        }
    }
}

