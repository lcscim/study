package com.example.asus.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Color_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        TextView tv2 = findViewById(R.id.ca2);
        TextView tv3 = findViewById(R.id.ca3);
        tv2.setBackgroundColor(0x00ff00);
        tv3.setBackgroundColor(0xff00ff00);

    }
}
