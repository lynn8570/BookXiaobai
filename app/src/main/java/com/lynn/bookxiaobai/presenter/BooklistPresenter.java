package com.lynn.bookxiaobai.presenter;

import android.content.Context;
import android.util.Log;

import com.lynn.bookxiaobai.entity.BooksResult;
import com.lynn.bookxiaobai.presenter.base.Presenter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lynn on 2018/2/11.
 */

public class BooklistPresenter extends Presenter.PresenterBase<BooksResult> {


    public BooklistPresenter(Context context){
        super(context);
    }

    public void getSearchBooks(String key, String tag, int start, int count) {


        mCompositeSubscription.add(manager.getSearchBooks(key, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksResult>() {
                    @Override
                    public void onCompleted() {
                        Log.i("linlian","onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("linlian","onCompleted");
                    }

                    @Override
                    public void onNext(BooksResult booksResult) {
                        Log.i("linlian","onCompleted  booksResult="+booksResult);
                        mView.onSuccess(booksResult);
                    }
                }));
    }
}
