package com.lynn.bookxiaobai;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.lynn.bookxiaobai.boxstore.BookBeanMiniBox;
import com.lynn.bookxiaobai.entity.BookBeanMini;
import com.lynn.bookxiaobai.entity.BookBeanMini_;
import com.lynn.bookxiaobai.util.DBfile;
import com.lynn.bookxiaobai.util.DirTraversal;
import com.lynn.bookxiaobai.util.TimeUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lynn on 2018/3/5.
 */
@RunWith(AndroidJUnit4.class)
public class TestDataBase {

    private BookBeanMiniBox miniBox;

    @Before
    public void insertdata() throws Exception {
        miniBox=new BookBeanMiniBox(BookBeanMini.class);
        BookBeanMini result = miniBox.getQueryBuilder().equal(BookBeanMini_.titile, "小黑子").build().findFirst();

        if (result == null || TextUtils.isEmpty(result.getTitile())) {
            miniBox = new BookBeanMiniBox(BookBeanMini.class);
            BookBeanMini bookBeanMini = new BookBeanMini();
            bookBeanMini.setTitile("小黑子");
            bookBeanMini.setAuthor("法。圣艾克苏佩里");
            bookBeanMini.setState(BookBeanMini.STATE_UNREADED);
            bookBeanMini.setStateTime(TimeUtil.getTime(null));
            bookBeanMini.setIsbn10("11111");
            bookBeanMini.setIsbn13("22222");
            bookBeanMini.setSummary("summary tester");
            miniBox.insert(bookBeanMini);
        }

    }

    @Test
    public void testquery() throws Exception {
        BookBeanMini result = miniBox.getQueryBuilder().equal(BookBeanMini_.titile, "小黑子").build().findFirst();
        assertNotNull(result);
        Assert.assertEquals(result.getAuthor(), "法。圣艾克苏佩里");
    }

    @Test
    public void testDBexists() throws Exception {
        Assert.assertTrue(DBfile.isDBExsist());

    }

    @Test
    public void testCreateDB() throws Exception {
        Assert.assertTrue(DBfile.createDBfile());
    }

    @Test
    public void testZipDB() throws Exception {
        DBfile.zipDBfile();

        File file = new File(DBfile.ZIP_FILE_PATH+DBfile.ZIP_FILE_NAME);

        Assert.assertTrue(file.exists());

    }

    @Test
    public void testunZipDB() throws Exception {

        DBfile.unZipDBfile(DBfile.ZIP_FILE_PATH+DBfile.ZIP_FILE_NAME);
        ArrayList<File> files = DirTraversal.listFiles(DBfile.DB_PATH + "testdir");
        int size = files.size();

        ArrayList<File> filesOrigin = DirTraversal.listFiles(DBfile.DB_PATH);
        int sizeOrigin = filesOrigin.size();

        Assert.assertEquals(size, sizeOrigin);

    }
}
