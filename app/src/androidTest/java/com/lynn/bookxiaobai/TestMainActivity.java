package com.lynn.bookxiaobai;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.lynn.bookxiaobai.ui.BookDetailActivity;
import com.lynn.bookxiaobai.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TestMainActivity {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);



}
