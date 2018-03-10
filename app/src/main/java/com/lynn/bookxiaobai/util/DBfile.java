package com.lynn.bookxiaobai.util;

import android.os.Environment;
import android.util.Log;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lynn on 2018/3/5.
 */

public class DBfile {

    public static final String TAG = "DBfile";

    public static final String DB_PATH = "./data/data/com.lynn.bookxiaobai/files/objectbox/objectbox/";

    public static final String ZIP_FILE_PATH = Environment.getExternalStorageDirectory().toString();
    public static final String ZIP_FILE_NAME = "/DB.zip";

    public static boolean isDBExsist() {

        File dataDB = new File(DB_PATH + "data.mdb");
        File lockDB = new File(DB_PATH + "lock.mdb");

        if (dataDB.exists() && lockDB.exists()) {
            return true;
        }
        return false;
    }


    public static boolean createDBfile() {
        File testDB = new File(DB_PATH + "test.mdb");
        try {
            if (testDB.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean zipDBfile() {

        ArrayList<File> files = DirTraversal.listFiles(DB_PATH);
        if (files == null) return false;

        for (int i = 0; i < files.size(); i++) {
            File temp = files.get(i);
            Log.i(TAG, "zipDBFile item i=" + i + " path =" + temp.getAbsolutePath());
        }

        File newZipfile = DirTraversal.getFilePath(ZIP_FILE_PATH, ZIP_FILE_NAME);

        Log.i(TAG, "newZipfile= " + newZipfile);
        if (newZipfile == null) return false;
        if (!newZipfile.exists()) {
            try {
                newZipfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            ZipUtils.zipFiles(files, newZipfile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("length=" + newZipfile.length());

        return true;
    }

    public static void unZipDBfile(String zipFile) {

        try {

            deleteExistDBfile();
            ZipUtils.unzip(zipFile/*DB_PATH + "DB.zip"*/, DB_PATH );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExistDBfile(){
        ArrayList<File> files = DirTraversal.listFiles(DB_PATH);
        if (files == null) return ;

        for (int i = 0; i < files.size(); i++) {
            File temp = files.get(i);
            if(temp.exists()&&temp.isFile()){
                temp.delete();
            }
            Log.i(TAG, "zipDBFile item i=" + i + " path =" + temp.getAbsolutePath());
        }
    }
}
