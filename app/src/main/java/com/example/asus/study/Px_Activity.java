package com.example.asus.study;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.asus.study.util.DisplayUtil;

public class Px_Activity extends AppCompatActivity {
    private TextView tv_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_px);
        tv_1 = findViewById(R.id.btn_dp2px);
        int width = DisplayUtil.getSreenWidth(this);
        int height = DisplayUtil.getSreenHeight(this);
        float density = DisplayUtil.getSreenDensity(this);
        String info = String.format("当前屏幕的宽度是%dpx，高度是%dpx，像素密度是%f",
                width, height, density);
        tv_1.setText(info);
    }

}
