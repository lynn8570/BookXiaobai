package com.lynn.bookxiaobai.boxstore;

import android.content.Context;
import android.util.Log;

import com.lynn.bookxiaobai.BuildConfig;
import com.lynn.bookxiaobai.entity.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by lynn on 2018/2/10.
 */

public class BoxConfig {

    private static BoxStore boxStore;
    private static final String TAG="BoxConfig";

    public static void init(Context app) {

        Log.i(TAG,"init()............");
        BoxConfig.boxStore = MyObjectBox.builder().androidContext(app).build();
        Log.i(TAG,"init()............boxstore="+boxStore);
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(app);

        }
    }

    public static BoxStore getBoxStore() {
        Log.i(TAG,"getBoxStore()............boxstore="+boxStore);
        if (boxStore == null) {
            throw new NullPointerException("did not init BoxConfig");
        }
        return boxStore;
    }
}
