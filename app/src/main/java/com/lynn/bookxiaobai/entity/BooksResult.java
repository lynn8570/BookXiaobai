package com.lynn.bookxiaobai.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class BooksResult {

    private int count;
    private int start;
    private int total;

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }

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
