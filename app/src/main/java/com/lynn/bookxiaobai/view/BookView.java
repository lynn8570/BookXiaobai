package com.lynn.bookxiaobai.view;

import com.lynn.bookxiaobai.entity.BookBean;

/**
 * Created by lynn on 2018/2/7.
 */

public interface BookView extends View {

    void onSuccess(BookBean bookBean);

    void onError(String msg);

    void onChange(BookBean bookBean);

}
