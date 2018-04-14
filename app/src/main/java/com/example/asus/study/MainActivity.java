package com.example.asus.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_px).setOnClickListener(this);
        findViewById(R.id.btn_color).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_px:
                Intent intent = new Intent(MainActivity.this,Px_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_color:
                Intent intent1 = new Intent(MainActivity.this,Color_Activity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
