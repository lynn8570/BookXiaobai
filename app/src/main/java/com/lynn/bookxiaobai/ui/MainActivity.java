package com.lynn.bookxiaobai.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.boxstore.BookBeanMiniBox;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.presenter.BookPresenter;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.view.BookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.reactive.DataSubscriptionList;

public class MainActivity extends Activity {

    private BookPresenter mBookPresenter = new BookPresenter(this);
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<BookBeanMini> mDataList = new ArrayList<BookBeanMini>();


    private BookBeanMiniBox mBeanMiniBox;
    @BindView(R.id.img_scan)
    ImageView imageViewScan;

    @BindView(R.id.btn_search)
    Button buttonSearch;

    @BindView(R.id.edit_search)
    EditText editTextSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        imageViewScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch = String.valueOf(editTextSearch.getText());
                if (!TextUtils.isEmpty(strSearch)) {
//                   mBookPresenter.getSearchBooks(strSearch,"",0,10);
                    Intent intent = new Intent(MainActivity.this, SearchListActivity.class);
                    intent.putExtra("key", strSearch);
                    startActivity(intent);
                }
            }
        });


        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);


        initTimeline();
    }

    @Override
    protected void onDestroy() {
        dataSubscriptionList.cancel();
        super.onDestroy();
    }

    private DataSubscriptionList dataSubscriptionList = new DataSubscriptionList();

    private void initTimeline() {

//        loadData();
        mTimeLineAdapter = new TimeLineAdapter(mDataList);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        mBeanMiniBox = new BookBeanMiniBox(BookBeanMini.class);
        mBeanMiniBox.bindBoxListAdapter(mTimeLineAdapter, dataSubscriptionList);

        mTimeLineAdapter.setItemClick(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Log.i("linlian", "view=" + view);
                Log.i("linlian", "positon=" + positon);
                BookBeanMini bm = mTimeLineAdapter.getmFeedlist().get(positon);
                Log.i("linlian", "bm=" + bm);
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("id", bm.getId());
                startActivity(intent);
            }

            @Override
            public void onStateClick(View view, int position) {
                Log.i("linlian", "onStateClick positon=" + position);
                BookBeanMini bm = mTimeLineAdapter.getmFeedlist().get(position);
                bm.setState(bm.getState() == BookBeanMini.STATE_UNREADED?BookBeanMini.STATE_READED:BookBeanMini.STATE_UNREADED);
                bm.setStateTime(TimeUtil.getTime(null));
                Log.i("linlian", "onStateClick bm=" + bm);
                mBeanMiniBox.update(bm);

            }
        });


    }

    private void loadData() {
        mDataList.add(new BookBeanMini("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("深度工作", "〔美〕卡尔·纽波特 ", 2, "", "2018-01-12 08:00"));
        mDataList.add(new BookBeanMini("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作", "2018-02-12 08:00"));
        mDataList.add(new BookBeanMini("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("深度工作", "〔美〕卡尔·纽波特 ", 2, "", "2018-01-12 08:00"));
        mDataList.add(new BookBeanMini("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作", "2018-02-12 08:00"));
        mDataList.add(new BookBeanMini("精神科的故事：锅男dadaadada", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("深度工作", "〔美〕卡尔·纽波特 ", 2, "", "2018-01-12 08:00"));
        mDataList.add(new BookBeanMini("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作", "2018-02-12 08:00"));
        mDataList.add(new BookBeanMini("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。", "2017-02-12 08:00"));
        mDataList.add(new BookBeanMini("深度工作", "〔美〕卡尔·纽波特 ", 2, "", "2018-01-12 08:00"));
        mDataList.add(new BookBeanMini("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作", "2018-02-12 08:00"));
        mDataList.add(new BookBeanMini("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。", "2017-02-12 08:00"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String strIsbn = result.getContents();
                if (!TextUtils.isEmpty(strIsbn)) {
                    mBookPresenter.getBookByIsbn(strIsbn);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private BookView<BookBean> mBookView = new BookView<BookBean>() {
        @Override
        public void onSuccess(BookBean book) {
            //startdetail activity
            Log.i("linlian", "book=" + book);
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BookBean", book);
            startActivity(intent);
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCompleted() {

        }


    };


}
