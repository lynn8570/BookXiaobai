package com.lynn.bookxiaobai.manager;

import android.content.Context;

import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BooksResult;
import com.lynn.bookxiaobai.retrofit.RetrofitHelper;
import com.lynn.bookxiaobai.retrofit.RetrofitService;

import rx.Observable;

/**
 * Created by Administrator on 2018/2/7.
 * RetrofitService
 *
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getService();
    }

    public Observable<BooksResult> getSearchBooks(String key, String tag, int start, int count){
        return mRetrofitService.getSearchBook(key,tag,start,count);
    }
    public Observable<BookBean> getBookByIsbn(String strIsbn){
        return  mRetrofitService.getBookByIsbn(strIsbn);
    }

    public Observable<BookBean> getBookById(String strId){
        return  mRetrofitService.getBookById(strId);
    }
}
