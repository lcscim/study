package com.example.asus.study;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_px).setOnClickListener(this);
        findViewById(R.id.btn_color).setOnClickListener(this);
        findViewById(R.id.btn_pmd).setOnClickListener(this);
        findViewById(R.id.btn_lts).setOnClickListener(this);
        findViewById(R.id.btn_spinner).setOnClickListener(this);
        findViewById(R.id.btn_dp).setOnClickListener(this);
        findViewById(R.id.btn_vp).setOnClickListener(this);
        findViewById(R.id.btn_qdy).setOnClickListener(this);

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
            case R.id.btn_pmd:
                Intent intent2 = new Intent(MainActivity.this,View_Activity.class);
                startActivity(intent2);
                break;
            case R.id.btn_lts:
                Intent intent3 = new Intent(MainActivity.this,LTS_Activity.class);
                startActivity(intent3);
                break;
            case R.id.btn_spinner:
                Intent intent4 = new Intent(MainActivity.this,Spinner_Activity.class);
                startActivity(intent4);
                break;
            case R.id.btn_dp:
                Intent intent5 = new Intent(MainActivity.this,Calender_Activity.class);
                startActivity(intent5);
                break;
            case R.id.btn_vp:
                Intent intent6 = new Intent(MainActivity.this,ViewPage_Activity.class);
                startActivity(intent6);
                break;
            case R.id.btn_qdy:
                Intent intent7 = new Intent(MainActivity.this,QDY_Activity.class);
                startActivity(intent7);
                break;
            default:
                break;
        }
    }

}
