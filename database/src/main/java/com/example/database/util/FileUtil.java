package com.example.database.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil {

    public static void saveText(String path, String txt) {
        try (BufferedWriter os = new BufferedWriter(new FileWriter(path))) {
            os.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readText(String path) {
        BufferedReader is;
        StringBuilder sb = new StringBuilder();
        try {
            is = new BufferedReader(new FileReader(path));
            String line;
            while ((line = is.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    //把位图数据保存到指定路径的图片文件
    public static void saveImage(String path, Bitmap bitmap) {
        try (OutputStream fos = new FileOutputStream(path)) {
            //将文件数据压缩到文件输出流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Bitmap readImage(String path) {
        Bitmap bitmap;
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bitmap;
    }
}
