package com.lynn.bookxiaobai.util;

import android.os.Environment;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lynn on 2018/3/5.
 */

public class DBfile {

    public  static final String DB_PATH="./data/data/com.lynn.bookxiaobai/files/objectbox/objectbox/";

    public static final String ZIP_FILE_PATH= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    public static final String ZIP_FILE_NAME= "DB.zip";

    public static boolean isDBExsist(){
        
        File dataDB = new File(DB_PATH+"data.mdb");
        File lockDB = new File(DB_PATH+"lock.mdb");

        if(dataDB.exists()&&lockDB.exists()){
            return true;
        }
        return false;
    }


    public static boolean createDBfile() {
        File testDB = new File(DB_PATH+"test.mdb");
        try {
            if(testDB.createNewFile()){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void zipDFfile(){

        ArrayList<File> files = DirTraversal.listFiles(DB_PATH);
        Assert.assertNotNull(files);
        for (int i=0;i<files.size();i++){
            File temp = files.get(i);
            System.out.println(temp.getName());
        }

        File newZipfile = DirTraversal.getFilePath(ZIP_FILE_PATH,ZIP_FILE_NAME);

        System.out.println("length="+newZipfile.length());

        try {
            ZipUtils.zipFiles(files,newZipfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("length="+newZipfile.length());

    }

    public static void unZipDBfile(){

        try {
            ZipUtils.unzip(DB_PATH+"DB.zip",DB_PATH+"testdir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
