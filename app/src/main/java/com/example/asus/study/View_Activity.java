package com.example.asus.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import static android.text.TextUtils.TruncateAt.END;
import static android.text.TextUtils.TruncateAt.MARQUEE;

public class View_Activity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        final TextView textView = findViewById(R.id.tv_pmd);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==0){
                    textView.setEllipsize(END);
                    i = 1;
                }else if (i==1){
                    textView.setEllipsize(MARQUEE);
                    i = 0;
                }
            }
        });
    }
}
