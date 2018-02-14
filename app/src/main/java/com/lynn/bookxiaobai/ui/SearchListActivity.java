package com.lynn.bookxiaobai.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BooksResult;
import com.lynn.bookxiaobai.presenter.BooklistPresenter;
import com.lynn.bookxiaobai.view.BookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchListActivity extends ActivityBase {

    private String mKey;
    private BooklistPresenter booklistPresenter = new BooklistPresenter(SearchListActivity.this);

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BookItemAdapter bookItemAdapter;

    private List<BookBean> mFeedList = new ArrayList<BookBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(SearchListActivity.this);
        setupHomeAsUpToolBar();


        booklistPresenter.onCreate();
        booklistPresenter.attachView(new BookView<BooksResult>() {
            @Override
            public void onSuccess(BooksResult data) {

                Log.i("linlian", "data=" + data);

                if (data != null && data.getBooks() != null) {
                    mFeedList = data.getBooks();
                    bookItemAdapter.updateList(mFeedList);
                }

            }

            @Override
            public void onError(String msg) {
                Log.i("linlian", "msg=" + msg);
            }

            @Override
            public void onCompleted() {
                Log.i("linlian", "onCompleted=");
            }
        });
        mKey = getIntent().getStringExtra("key");

        if (!TextUtils.isEmpty(mKey)) {

            booklistPresenter.getSearchBooks(mKey, null, 0, 10);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(SearchListActivity.this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setHasFixedSize(true);

        bookItemAdapter = new BookItemAdapter(mFeedList);
        recyclerView.setAdapter(bookItemAdapter);

        bookItemAdapter.setItemClick(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Log.i("linlian", "position=" + positon);
                BookBean bookBean = mFeedList.get(positon);
                Log.i("linlian", "bookBean=" + bookBean);
                Intent intent = new Intent(SearchListActivity.this, BookDetailActivity.class);
                intent.putExtra("BookBean", bookBean);
                startActivity(intent);
            }

            @Override
            public void onStateClick(View view,int position) {

            }
        });


    }
}
