package com.example.asus.study.util;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    public static String getNowDateTime() {
        SimpleDateFormat s_format = new SimpleDateFormat("yyyyMMddhhmmss"); //格式化日期
        return s_format.format(new Date());     //传入当前日期并返回
    }

    @SuppressLint("SimpleDateFormat")
    public static String getNowTime() {
        SimpleDateFormat s_format = new SimpleDateFormat("HH:mm:ss");   //格式化时间
        return s_format.format(new Date());     //传入当前时间并返回

    }

}
