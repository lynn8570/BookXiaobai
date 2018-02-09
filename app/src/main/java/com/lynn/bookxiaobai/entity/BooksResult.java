package com.lynn.bookxiaobai.entity;

/**
 * Created by Administrator on 2018/2/7.
 */

public class BooksResult {

    private int count;
    private int start;
    private int total;
    private java.util.List<BookBean> books;
    @Override
    public String toString() {
        return "Book{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
