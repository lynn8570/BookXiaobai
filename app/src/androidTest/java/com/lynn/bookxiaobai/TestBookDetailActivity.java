package com.lynn.bookxiaobai;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.lynn.bookxiaobai.ui.BookDetailActivity;
import com.lynn.bookxiaobai.util.TimeUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TestBookDetailActivity {

    @Rule
    public ActivityTestRule<BookDetailActivity> mActivityTestRule = new ActivityTestRule<BookDetailActivity>(BookDetailActivity.class);

    @Test
    public void testStartBookDetail() throws Exception {

        Intent intent = new Intent(mActivityTestRule.getActivity(), BookDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivityTestRule.launchActivity(intent);

    }

    @Test
    public void testTimeSpan() throws Exception {

        String r = TimeUtil.parseDateTimeSpan("2018-02-12 08:00", "2018-02-13 23:03");
        System.out.println(r);

        String r1 = TimeUtil.parseDateTimeSpan("2018-02-13 22:40", "2018-02-13 23:03");
        System.out.println(r1);
        String r2 = TimeUtil.parseDateTimeSpan("2018-02-13 20:40", "2018-02-13 23:03");
        System.out.println(r2);
        assertNotNull(r1);

    }
}
