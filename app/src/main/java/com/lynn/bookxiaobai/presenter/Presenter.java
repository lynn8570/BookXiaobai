package com.lynn.bookxiaobai.presenter;

import android.content.Intent;

import com.lynn.bookxiaobai.view.View;

/**
 * Created by lynn on 2018/2/7.
 */


public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attatchIncomingIntent(Intent intent);
}

