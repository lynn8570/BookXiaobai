package com.lynn.bookxiaobai;

import android.app.Application;

import com.lynn.bookxiaobai.boxstore.BoxConfig;
import com.lynn.bookxiaobai.entity.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by lynn on 2018/2/10.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        BoxConfig.init(App.this);
    }
}
