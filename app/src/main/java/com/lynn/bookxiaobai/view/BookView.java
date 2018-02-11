package com.lynn.bookxiaobai.view;

import com.lynn.bookxiaobai.entity.BookBean;

/**
 * Created by lynn on 2018/2/7.
 */

public interface BookView<T> {
    void onSuccess(T data);

    void onError(String msg);

    void onCompleted();
}
