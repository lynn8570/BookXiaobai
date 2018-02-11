package com.lynn.bookxiaobai.entity;

import java.util.ArrayList;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by lynn on 2018/2/10.
 * only persist colum
 */

@Entity
public class BookBeanMini {
    public static final int STATE_UNSTART=0;//未收藏
    public static final int STATE_UNREADED = 1;//未读
    public static final int STATE_READED = 2;//已读


    @Id
    private long idbox;
    private String id;
    private String titile;
    private String author;
    private String isbn10;
    private String isbn13;
    private String author_intro;
    private String summary;
    private String price;
    private int state;
    private String stateTime;
    private String note;
    private String noteTime;


    public long getIdbox() {
        return idbox;
    }

    public void setIdbox(long idbox) {
        this.idbox = idbox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateTime() {
        return stateTime;
    }

    public void setStateTime(String stateTime) {
        this.stateTime = stateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BookBeanMini(){

    }

    public BookBeanMini(String title, String author, int state, String summary, String time) {
        setTitile(title);
        setAuthor(author);
        setState(state);
        setSummary(summary);
        setNoteTime(time);

    }}

