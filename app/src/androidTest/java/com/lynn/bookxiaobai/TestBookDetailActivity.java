package com.lynn.bookxiaobai;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.lynn.bookxiaobai.ui.BookDetailActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TestBookDetailActivity {

    @Rule
    public ActivityTestRule<BookDetailActivity> mActivityTestRule = new ActivityTestRule<BookDetailActivity>(BookDetailActivity.class);

    @Test
    public void testStartBookDetail() throws Exception{

        Intent intent = new Intent(mActivityTestRule.getActivity(), BookDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivityTestRule.launchActivity(intent);

    }
}
