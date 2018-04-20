package com.example.asus.study.util;

import com.example.asus.study.R;

import java.util.ArrayList;

public class GoodsInfo {
    public String name;
    public int pic;
    private static String[] mNameArray = {
            "OverWatch", "守望先锋", "OW", "耻辱2"};
    private static int[] mPicArray = {R.drawable.ow1,R.drawable.ow2,R.drawable.ow3,R.drawable.zr2};
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i=0; i<mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
