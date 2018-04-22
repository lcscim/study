package com.example.asus.study.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.study.util.GoodsInfo;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<ImageView> mViewList = new ArrayList<>();
    private ArrayList<GoodsInfo> mGoodList = new ArrayList<>();
    public ImagePagerAdapter(Context context,ArrayList<GoodsInfo> goodsList){
        mContext = context;
        mGoodList = goodsList;
        for (int i = 0;i<mGoodList.size();i++){
            ImageView view = new ImageView(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setImageResource(mGoodList.get(i).pic);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mViewList.add(view);
        }
    }
    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView(mViewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodList.get(position).name;
    }
}
