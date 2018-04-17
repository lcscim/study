package com.example.asus.study;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Calender_Activity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView tv_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        tv_date = findViewById(R.id.tv_date);
        findViewById(R.id.btn_dpd).setOnClickListener(this);
        findViewById(R.id.btn_tpd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        switch (v.getId()){
            case R.id.btn_dpd:
                DatePickerDialog dialog = new DatePickerDialog(this,this,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.btn_tpd:
                TimePickerDialog dialog1 = new TimePickerDialog(this,this,calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),true);
                dialog1.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("您选择的日期是%d年%d月%d日",year,month+1,dayOfMonth);       //月份要加一，因为从0开始
        tv_date.setText(desc);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("您选择的时间是%d时%d分",hourOfDay,minute);       //月份要加一，因为从0开始
        tv_date.setText(desc);
    }
}
