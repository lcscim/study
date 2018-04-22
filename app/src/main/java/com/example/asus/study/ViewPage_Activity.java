package com.example.asus.study;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;

import com.example.asus.study.adapter.ImagePagerAdapter;
import com.example.asus.study.util.GoodsInfo;

import java.util.ArrayList;

public class ViewPage_Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> goodsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        PagerTabStrip pagerTabStrip = findViewById(R.id.pts_title);     //只有在代码中才能修改pagertabstrip中文字的属性
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        pagerTabStrip.setTextColor(Color.GREEN);
        ViewPager viewPager = findViewById(R.id.vp_content);
        goodsList  = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this,goodsList);
        viewPager.setAdapter(adapter);      //设置页面适配器
        viewPager.setCurrentItem(0);        //设置当前页码
        viewPager.addOnPageChangeListener(this);        //设置页面切换监听器，需要重写以下三个方法
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {  //滑动过程中触发

    }

    @Override
    public void onPageSelected(int position) {      //滑动结束后触发
        Toast.makeText(this,"您查看的是"+goodsList.get(position).name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {       //状态发生变化时触发

    }
}
