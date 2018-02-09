package com.lynn.bookxiaobai.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.presenter.BookPresenter;
import com.lynn.bookxiaobai.view.BookView;


public class BookDetailActivity extends Activity {
    private TextView txtBookName;
    private TextView txtAuthor;
    private ImageView imgBook;
    private View view;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        BookBean bookBean = intent.getParcelableExtra("BookBean");


        txtBookName = (TextView) findViewById(R.id.txt_book_name);
        txtAuthor = (TextView) findViewById(R.id.txt_author);
        imgBook = (ImageView) findViewById(R.id.img_book);
        view = (View) findViewById(R.id.view);
        textView = (TextView) findViewById(R.id.textView);


        displayBookDetail(bookBean);
        testLoadBool();  //for test only

    }

    private void testLoadBool() {

        BookPresenter mBookPresenter = new BookPresenter(this);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(new BookView() {
            @Override
            public void onSuccess(BookBean bookBean) {
                displayBookDetail(bookBean);
            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onChange(BookBean bookBean) {

            }
        });
        mBookPresenter.getBookByIsbn("9787115412744");
        Toast.makeText(BookDetailActivity.this, "start request..", Toast.LENGTH_LONG).show();
    }

    private void displayBookDetail(BookBean bookBean) {
        if (bookBean != null) {
            txtBookName.setText(bookBean.getTitle());
            txtAuthor.setText(bookBean.getAuthor().get(0));
            textView.setText(bookBean.getSummary());
        }
    }
}
