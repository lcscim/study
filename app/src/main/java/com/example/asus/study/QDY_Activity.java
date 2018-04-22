package com.example.asus.study;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asus.study.adapter.ImagePagerAdapter;
import com.example.asus.study.adapter.LaunchSimpleAdapter;

import java.util.ArrayList;

public class QDY_Activity extends AppCompatActivity {

    public int[] ints = {R.drawable.guide_bg1,R.drawable.guide_bg2,R.drawable.guide_bg3,R.drawable.guide_bg4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qdy);
        ViewPager viewPager = findViewById(R.id.vp_start);
        LaunchSimpleAdapter launchSimpleAdapter = new LaunchSimpleAdapter(this,ints);
        viewPager.setAdapter(launchSimpleAdapter);
        viewPager.setCurrentItem(0);

    }

}
