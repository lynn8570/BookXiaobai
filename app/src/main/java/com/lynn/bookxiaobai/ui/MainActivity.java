package com.lynn.bookxiaobai.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.presenter.BookPresenter;
import com.lynn.bookxiaobai.view.BookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private BookPresenter mBookPresenter = new BookPresenter(this);
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<BookBean> mDataList = new ArrayList<BookBean>();


    @BindView(R.id.img_scan)
    ImageView imageViewScan;


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



        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        initTimeline();
    }

    private void initTimeline() {

        loadData();
        mTimeLineAdapter = new TimeLineAdapter(mDataList);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    private void loadData() {
        mDataList.add(new BookBean("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。","2017-02-12 08:00"));
        mDataList.add(new BookBean("深度工作", "〔美〕卡尔·纽波特 ", 2, "","2018-01-12 08:00"));
        mDataList.add(new BookBean("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作","2018-02-12 08:00"));
        mDataList.add(new BookBean("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。","2017-02-12 08:00"));
        mDataList.add(new BookBean("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。","2017-02-12 08:00"));
        mDataList.add(new BookBean("深度工作", "〔美〕卡尔·纽波特 ", 2, "","2018-01-12 08:00"));
        mDataList.add(new BookBean("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作","2018-02-12 08:00"));
        mDataList.add(new BookBean("精神科的故事：锅男dadaadada", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。","2017-02-12 08:00"));
        mDataList.add(new BookBean("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。","2017-02-12 08:00"));
        mDataList.add(new BookBean("深度工作", "〔美〕卡尔·纽波特 ", 2, "","2018-01-12 08:00"));
        mDataList.add(new BookBean("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作","2018-02-12 08:00"));
        mDataList.add(new BookBean("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。","2017-02-12 08:00"));
        mDataList.add(new BookBean("南极", "[爱尔兰] 克莱尔·吉根 ", 0, "南极》是爱尔兰短篇小说女王克莱尔·吉根经典小说集，被评为《洛杉矶时报》年度小说，获爱尔兰隆尼文学奖。","2017-02-12 08:00"));
        mDataList.add(new BookBean("深度工作", "〔美〕卡尔·纽波特 ", 2, "","2018-01-12 08:00"));
        mDataList.add(new BookBean("原则", "爱尔兰] 克莱尔·吉根 ", 1, "华尔街投资大神、对冲基金公司桥水创始人，人生经验之作","2018-02-12 08:00"));
        mDataList.add(new BookBean("精神科的故事：锅男", "[日] 奥田英朗 ", 0, "《精神科的故事：锅男》是奥田英朗又一部长篇小说代表作，也是《精神科的故事》系列完结篇。","2017-02-12 08:00"));
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

    private BookView mBookView = new BookView() {
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
        public void onChange(BookBean bookBean) {

        }
    };
}
