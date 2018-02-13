package com.lynn.bookxiaobai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lynn.bookxiaobai.R;
import com.lynn.bookxiaobai.boxstore.BookBeanMiniBox;
import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.presenter.BookPresenter;
import com.lynn.bookxiaobai.view.BookView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookDetailActivity extends AppCompatActivity {
    @BindView(R.id.txt_book_name)
    TextView txtBookName;
    @BindView(R.id.txt_author)
    TextView txtAuthor;
    @BindView(R.id.img_book)
    ImageView imgBook;
    @BindView(R.id.txt_summery)
    TextView textView;
    @BindView(R.id.img_star)
    ImageView imgStar;

    private BookBeanMiniBox mBookBeanMiniBox;

    private BookBean mBookbean;

    private BookPresenter bookPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ButterKnife.bind(BookDetailActivity.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("图书详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mBookBeanMiniBox = new BookBeanMiniBox(BookBeanMini.class);

        bookPresenter = new BookPresenter(BookDetailActivity.this);
        bookPresenter.onCreate();

        bookPresenter.attachView(new BookView<BookBean>() {
            @Override
            public void onSuccess(BookBean data) {
                if (data != null) {
                    displayBookDetail(data);
                }

            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onCompleted() {

            }
        });

        Intent intent = getIntent();
        mBookbean = intent.getParcelableExtra("BookBean");
        if (mBookbean == null) {
            String strId = intent.getStringExtra("id");
            if (TextUtils.isEmpty(strId)) {
                Toast.makeText(BookDetailActivity.this, "get id failed", Toast.LENGTH_SHORT).show();
            } else {
                requestBook(strId);
            }

        } else {
            displayBookDetail(mBookbean);
        }






        imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBookBeanMini == null) {
                    addBookBeanMini();
                } else {
                    removeBookBeanMini();
                }
            }
        });

    }

    private void requestBook(String strid) {

        Toast.makeText(BookDetailActivity.this, "request book", Toast.LENGTH_SHORT).show();
        bookPresenter.getBookById(strid);
    }

    private BookBeanMini mBookBeanMini;

    private void updateStar() {

        mBookBeanMini = mBookBeanMiniBox.query(mBookbean.getId());
        if (mBookBeanMini != null) {
            imgStar.setImageDrawable(ContextCompat.getDrawable(BookDetailActivity.this, R.drawable.ic_star));

        } else {
            imgStar.setImageDrawable(ContextCompat.getDrawable(BookDetailActivity.this, R.drawable.ic_unstar));
        }
    }

    private void addBookBeanMini() {
        long result = mBookBeanMiniBox.insert(mBookbean);
        if (result < 0) {
            Toast.makeText(BookDetailActivity.this, "add start error", Toast.LENGTH_LONG).show();
            mBookBeanMini = null;
        } else {
            updateStar();
        }

    }

    private void removeBookBeanMini() {

        mBookBeanMiniBox.delete(mBookBeanMini);
        updateStar();
    }

    private void displayBookDetail(BookBean bookBean) {
        mBookbean=bookBean;
        if (bookBean != null) {
            txtBookName.setText(bookBean.getTitle());
            txtAuthor.setText(bookBean.getAuthor().get(0));
            textView.setText(bookBean.getSummary());
        }
        updateStar();
    }
}
