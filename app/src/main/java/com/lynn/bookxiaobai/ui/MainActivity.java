package com.lynn.bookxiaobai.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.lynn.bookxiaobai.util.DBfile;
import com.lynn.bookxiaobai.util.TimeUtil;
import com.lynn.bookxiaobai.view.BookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.reactive.DataSubscriptionList;

public class MainActivity extends ActivityBase {
    private static final String TAG = "MainActivity";

    private BookPresenter mBookPresenter = new BookPresenter(this);
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<BookBeanMini> mDataList = new ArrayList<BookBeanMini>();

    private static final int REQUEST_CODE_ZIPFILE = 10;


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

        setUpToolBar();
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


    private void setUpToolBar() {
        setSupportActionBar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_export:

                        new ExportDBTask().execute();

                        break;
                    case R.id.action_import:

                        getZipFilePath();
                        break;

                }
                return true;
            }
        });
    }

    public void getZipFilePath() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(“image/*”);//选择图片
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_ZIPFILE);
    }

    public class ImportDBTash extends AsyncTask<String, Boolean, Void> {



        @Override
        protected Void doInBackground(String... paths) {
            String path = paths[0];
            DBfile.unZipDBfile(path);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this,"导入数据库完成，重启应用中",Toast.LENGTH_LONG).show();


            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.exit(0);
                    Intent i =new Intent(getBaseContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            },3000);


        }
    }

    private Handler mHandler = new Handler();


    public class ExportDBTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
            Toast.makeText(MainActivity.this, "正在导出数据库", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return DBfile.zipDBfile();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(MainActivity.this,
                        "已导出到文件：" + DBfile.ZIP_FILE_PATH + DBfile.ZIP_FILE_NAME, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "导出数据库失败", Toast.LENGTH_LONG).show();
            }

        }
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
                BookBeanMini bm = mTimeLineAdapter.getmFeedlist().get(positon);
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("id", bm.getId());
                startActivity(intent);
            }

            @Override
            public void onStateClick(View view, int position) {
                BookBeanMini bm = mTimeLineAdapter.getmFeedlist().get(position);
                bm.setState(bm.getState() == BookBeanMini.STATE_UNREADED ? BookBeanMini.STATE_READED : BookBeanMini.STATE_UNREADED);
                bm.setStateTime(TimeUtil.getTime(null));
                mBeanMiniBox.update(bm);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ZIPFILE && resultCode == Activity.RESULT_OK) {

            Uri uri = data.getData();
            if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
                String path = uri.getPath();
                Log.i(TAG, "pick zip file path =" + path);
                if (path.endsWith("zip")) {


                    new ImportDBTash().execute(path);
                    return;
                }

            }
            Toast.makeText(MainActivity.this, "请选择包含数据库的zip文件", Toast.LENGTH_LONG).show();

        } else {
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
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


}
