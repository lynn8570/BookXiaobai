package com.lynn.bookxiaobai;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.presenter.BookPresenter;
import com.lynn.bookxiaobai.view.BookView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2018/2/8.
 */
@RunWith(AndroidJUnit4.class)
public class TestDoubanApi {

    private BookView<BookBean> mtestBookView = new BookView<BookBean>() {
        @Override
        public void onSuccess(BookBean book) {
            assertNotNull(book);
            Log.i("TestDoubanApi ","book = "+book);
            assertEquals("精通Android网络开发",book.getTitle());
            assertEquals(testId,book.getId());
//            Intent intent = new Intent(appContext, BookDetailActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("BookBean",book);
//            appContext.startActivity(intent);
//可单独写activity的测试

        }

        @Override
        public void onError(String result) {
            assertNotNull(result);
        }

        @Override
        public void onCompleted() {

        }


    };
    private final String  testIsbn="9787115412744";
    private BookPresenter bookPresenter;
    private Context appContext;
    private final String testId="27110821";

    @Before
    public void initPresent() throws Exception{
        appContext = InstrumentationRegistry.getTargetContext();
        bookPresenter = new BookPresenter(appContext);
        bookPresenter.onCreate();
        bookPresenter.attachView(mtestBookView);
    }

    @Test
    public void testgetBookByIsbn()throws Exception{

        bookPresenter.getBookByIsbn(testIsbn);
    }

    @Test
    public void testgetBookById()throws Exception{

        bookPresenter.getBookByIsbn(testId);
    }


}